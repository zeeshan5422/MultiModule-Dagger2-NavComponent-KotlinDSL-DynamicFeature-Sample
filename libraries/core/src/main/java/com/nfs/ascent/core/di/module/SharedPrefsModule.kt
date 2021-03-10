package com.nfs.ascent.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.nfs.ascent.core.di.APP_CONTEXT
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object SharedPrefsModule {

    @Provides
    fun provideSharedPrefs(@Named(APP_CONTEXT) context: Context, name: String): SharedPreferences =
        context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)

}