package com.nfs.ascent.nfslogin.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SettingDTO(@SerializedName("appName") val appName: String)
