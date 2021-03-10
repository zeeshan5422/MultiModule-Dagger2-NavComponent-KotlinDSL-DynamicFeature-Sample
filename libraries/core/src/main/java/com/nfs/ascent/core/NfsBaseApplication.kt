package com.nfs.ascent.core

import android.app.Activity
import android.app.Application
import android.content.Context
import com.nfs.ascent.core.di.CoreComponent
import com.nfs.ascent.core.di.DaggerCoreComponent

open class NfsBaseApplication : Application() {

//    private val coreComponent: CoreComponent by lazy {
//        DaggerCoreComponent.factory().create(this)
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//    }
//
//    companion object {
//        @JvmStatic
//        fun coreComponent(context: Context) =
//            (context.applicationContext as NfsBaseApplication).coreComponent
//    }
}

//fun Activity.coreComponent() = NfsBaseApplication.coreComponent(this)