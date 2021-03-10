package com.nfs.ascent.core.di

import android.app.Application
import android.content.Context
import com.nfs.ascent.core.data.LocalizationItemDao
import com.nfs.ascent.core.db.CoreDatabase
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

interface CoreModuleDependencies {

    fun provideOkHttpClient(): OkHttpClient

    fun provideGsonConverterFactory(): GsonConverterFactory

    fun provideCoreDatabase(): CoreDatabase

    fun provideLocalizationItemDao(): LocalizationItemDao

    fun provideApplication(): Application

    @Named(APP_CONTEXT)
    fun provideAppContext(): Context

}