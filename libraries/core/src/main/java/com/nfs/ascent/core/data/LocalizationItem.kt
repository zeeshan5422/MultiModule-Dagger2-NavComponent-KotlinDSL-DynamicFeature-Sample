package com.nfs.ascent.core.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_localization")
data class LocalizationItem(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    val usename: String,
    val body: String
)