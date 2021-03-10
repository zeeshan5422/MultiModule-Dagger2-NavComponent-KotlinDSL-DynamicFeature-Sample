package com.nfs.ascent.nfslogin.data.source

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import com.nfs.ascent.nfslogin.data.model.SettingEntity

interface LoginDataSource


interface RemoteLoginDataSource : LoginDataSource {

    suspend fun getSettings(): ApiResponse<SettingDTO>
}

interface LocalLoginDataSource : LoginDataSource {

    suspend fun getSettings(): SettingEntity?
    suspend fun saveSettings(settingEntity: SettingEntity): Long
    suspend fun deleteSettings()
}