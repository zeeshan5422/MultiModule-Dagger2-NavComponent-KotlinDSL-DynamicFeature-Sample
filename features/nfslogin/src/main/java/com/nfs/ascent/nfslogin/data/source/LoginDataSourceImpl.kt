package com.nfs.ascent.nfslogin.data.source

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.data.LoginApi
import com.nfs.ascent.nfslogin.data.SettingsDao
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(private val loginApi: LoginApi) :
    RemoteLoginDataSource {

    override suspend fun getSettings(): ApiResponse<SettingDTO> =
        loginApi.handleRequest {
            loginApi.getSettings()
        }

}

class LocalLoginDataSourceImpl @Inject constructor(private val settingsDao: SettingsDao) :
    LocalLoginDataSource {
    override suspend fun getSettings(): SettingEntity {
        TODO("Not yet implemented")
    }

    override suspend fun saveSettings(settingEntity: SettingEntity): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSettings() {
        TODO("Not yet implemented")
    }

}