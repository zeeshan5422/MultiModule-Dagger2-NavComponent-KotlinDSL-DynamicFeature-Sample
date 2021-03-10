package com.nfs.ascent.nfslogin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nfs.ascent.nfslogin.data.model.SettingEntity

@Database(entities = [SettingEntity::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
}