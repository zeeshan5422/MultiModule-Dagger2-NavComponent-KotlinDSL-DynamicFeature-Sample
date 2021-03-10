package com.nfs.ascent.nfslogin.di

import android.app.Application
import androidx.room.Room
import com.nfs.ascent.core.db.DATABASE_NAME
import com.nfs.ascent.nfslogin.data.LoginDatabase
import com.nfs.ascent.nfslogin.data.SettingsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginDatabaseModule {

    @Provides
    fun provideLoginDatabase(application: Application): LoginDatabase {
        return Room.databaseBuilder(
            application,
            LoginDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSettingsDao(appDatabase: LoginDatabase): SettingsDao =
        appDatabase.settingsDao()

}