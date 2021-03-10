package com.nfs.ascent.nfslogin.data.repository

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import com.nfs.ascent.nfslogin.data.model.SettingEntity

interface LoginRepository {
    suspend fun getSettingsFromRemote(): ApiResponse<SettingEntity?>
    suspend fun getSettingsFromLocal(): ApiResponse<SettingEntity?>
    suspend fun saveSettingEntity(settingEntity: SettingEntity)
    suspend fun deleteSettings()
}