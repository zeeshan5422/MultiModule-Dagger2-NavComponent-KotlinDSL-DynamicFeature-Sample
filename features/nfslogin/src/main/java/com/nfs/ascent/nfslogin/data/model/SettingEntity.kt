package com.nfs.ascent.nfslogin.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Settings")
data class SettingEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    val appName: String
)
