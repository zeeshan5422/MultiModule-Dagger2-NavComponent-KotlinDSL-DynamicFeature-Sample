package com.nfs.ascent.nfslogin.data.repository

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.core.domain.repo.ErrorCode
import com.nfs.ascent.core.domain.repo.ErrorResponse
import com.nfs.ascent.nfslogin.data.mapper.DTOtoEntityMapper
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import com.nfs.ascent.nfslogin.data.source.LocalLoginDataSource
import com.nfs.ascent.nfslogin.data.source.RemoteLoginDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localDataSource: LocalLoginDataSource,
    private val remoteDataSource: RemoteLoginDataSource,
    private val mapper: DTOtoEntityMapper
) :
    LoginRepository {

    override suspend fun getSettingsFromRemote(): ApiResponse<SettingEntity?> {
        val settings: ApiResponse<SettingDTO> = remoteDataSource.getSettings()
        return mapper.map(settings)
    }

    override suspend fun getSettingsFromLocal(): ApiResponse<SettingEntity?> {
        val settings: SettingEntity? = localDataSource.getSettings()
        settings?.let {
            return ApiResponse.Success(it)
        } ?: run {
            return ApiResponse.Failure(ErrorResponse(code = ErrorCode.NOT_FOUND))
        }

    }

    override suspend fun saveSettingEntity(settingEntity: SettingEntity) {
        localDataSource.saveSettings(settingEntity)
    }

    override suspend fun deleteSettings() {
        localDataSource.deleteSettings()
    }
}