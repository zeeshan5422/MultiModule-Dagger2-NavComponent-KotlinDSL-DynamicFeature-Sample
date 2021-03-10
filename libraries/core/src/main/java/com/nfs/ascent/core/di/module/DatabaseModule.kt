package com.nfs.ascent.core.di.module

import android.app.Application
import androidx.room.Room
import com.nfs.ascent.core.data.LocalizationItemDao
import com.nfs.ascent.core.db.CoreDatabase
import com.nfs.ascent.core.db.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCoreDatabase(application: Application): CoreDatabase {
        return Room.databaseBuilder(
            application,
            CoreDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalizationItemDao(appDatabase: CoreDatabase): LocalizationItemDao =
        appDatabase.localizationDao()

}
