package com.nfs.ascent.core.di.module

import android.app.Application
import android.content.Context
import com.nfs.ascent.core.di.APP_CONTEXT
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class, SharedPrefsModule::class])
class CoreModule {

    @Provides
    @Singleton
    @Named(APP_CONTEXT)
    fun provideAppContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())
}