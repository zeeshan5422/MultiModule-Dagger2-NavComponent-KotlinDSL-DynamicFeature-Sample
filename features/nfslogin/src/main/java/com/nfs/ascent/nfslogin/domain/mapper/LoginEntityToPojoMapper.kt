package com.nfs.ascent.nfslogin.domain.mapper

import com.nfs.ascent.nfslogin.data.mapper.Mapper
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import com.nfs.ascent.nfslogin.domain.model.SettingsPojo
import javax.inject.Inject

class LoginEntityToPojoMapper @Inject constructor() : Mapper<SettingEntity, SettingsPojo> {
    override fun map(input: SettingEntity): SettingsPojo {
        return SettingsPojo(input.appName)
    }
}