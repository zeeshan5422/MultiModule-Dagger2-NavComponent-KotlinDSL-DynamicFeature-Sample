package com.nfs.ascent.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nfs.ascent.core.data.LocalizationItem
import com.nfs.ascent.core.data.LocalizationItemDao

const val DATABASE_NAME = "core_db.db"

@Database(
    entities = [LocalizationItem::class],
    version = 1,
    exportSchema = true
)
abstract class CoreDatabase : RoomDatabase() {

    abstract fun localizationDao(): LocalizationItemDao

}