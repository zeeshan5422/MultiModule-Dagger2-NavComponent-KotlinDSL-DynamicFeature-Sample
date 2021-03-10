package com.nfs.ascent.nfslogin.domain

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import com.nfs.ascent.nfslogin.data.repository.LoginRepository
import com.nfs.ascent.nfslogin.domain.mapper.LoginEntityToPojoMapper
import com.nfs.ascent.nfslogin.domain.model.SettingsPojo
import com.smarttoolfactory.domain.error.EmptyDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val mapper: LoginEntityToPojoMapper
) {

    /**
     * Function to retrieve data from repository with offline-last which checks
     * REMOTE data source first.
     *
     * * Check out Remote Source first
     * * If empty data or null returned throw empty set exception
     * * If error occurred while fetching data from remote: Try to fetch data from db
     * * If data is fetched from remote source: delete old data, save new data and return new data
     * * If both network and db don't have any data throw empty set exception
     *
     */
    fun getSettings(): Flow<ApiResponse<SettingsPojo>> {

        return flow {
            emit(loginRepository.getSettingsFromRemote())
        }.map {
            if (it.isSuccessful) {
                loginRepository.run {
                    deleteSettings()
                    saveSettingEntity(it.data!!)
                    getSettingsFromLocal()
                }
            } else {
                throw(EmptyDataException("Unable to fetch data from remote data source!"))
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emitAll(flowOf(loginRepository.getSettingsFromLocal()))
            }.map {
                mapToSettingsPojo(it)
            }
    }

    private fun mapToSettingsPojo(settingsResponse: ApiResponse<SettingEntity?>): ApiResponse<SettingsPojo> {
        return if (settingsResponse.isSuccessful) {
            ApiResponse.Success(mapper.map(settingsResponse.data!!))
        } else {
            ApiResponse.Failure(settingsResponse.errorResponse)
        }
    }

}
