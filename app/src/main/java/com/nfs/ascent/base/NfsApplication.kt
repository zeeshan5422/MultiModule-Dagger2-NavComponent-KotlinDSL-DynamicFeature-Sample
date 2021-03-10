package com.nfs.ascent.base

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.nfs.ascent.core.di.CoreComponent
import com.nfs.ascent.core.di.DaggerCoreComponent
import com.nfs.ascent.core.utils.NfsLogUtils

import okhttp3.OkHttpClient

class NfsApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    // if you want to inject specific object from application graph do like this,
    private val okHttpClient: OkHttpClient by lazy {
        coreComponent.provideOkHttpClient()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as NfsApplication).coreComponent
    }

    init {
        NfsLogUtils.d("NfsApplication <<< " + this.hashCode() + " >>>")
    }
}

fun Activity.coreComponent() = NfsApplication.coreComponent(this)

fun Fragment.coreComponent() = NfsApplication.coreComponent(requireActivity())