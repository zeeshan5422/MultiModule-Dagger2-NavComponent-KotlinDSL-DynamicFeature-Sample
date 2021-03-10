package com.nfs.ascent.core.data

import androidx.room.*

@Dao
interface LocalizationItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LocalizationItem): Long

    @Delete
    suspend fun deletePost(entity: LocalizationItem): Int

    @Query("DELETE FROM app_localization")
    suspend fun deleteAll()

}