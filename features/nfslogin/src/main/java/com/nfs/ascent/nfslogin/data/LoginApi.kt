package com.nfs.ascent.nfslogin.data

import com.nfs.ascent.core.domain.CoreApiInterface
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import retrofit2.Response
import retrofit2.http.GET

interface LoginApi : CoreApiInterface {

    @GET(URL_SETTINGS)
    suspend fun getSettings(): Response<SettingDTO>

    companion object {
        var BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}

const val MODULE_NAME = ""
const val URL_SETTINGS = MODULE_NAME + "/settings"


