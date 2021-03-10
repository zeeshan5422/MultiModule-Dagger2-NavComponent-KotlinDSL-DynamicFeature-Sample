package com.nfs.ascent.nfslogin.data

import androidx.room.*
import com.nfs.ascent.nfslogin.data.model.SettingEntity

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SettingEntity): Long

    @Delete
    suspend fun deletePost(entity: SettingEntity): Int

    @Query("DELETE FROM settings")
    suspend fun deleteAll()

}