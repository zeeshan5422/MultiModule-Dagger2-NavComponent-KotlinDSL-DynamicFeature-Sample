package com.nfs.ascent.core.di

import android.app.Application
import com.nfs.ascent.core.di.module.CoreModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

const val APP_CONTEXT = "APP_CONTEXT"
const val ACTIVITY_CONTEXT = "ACTIVITY_CONTEXT"
const val FRAGMENT_CONTEXT = "FRAGMENT_CONTEXT"

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent : CoreModuleDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }
}