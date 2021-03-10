package com.nfs.ascent.core.di

import android.app.Activity
import android.app.Service
import androidx.fragment.app.Fragment

interface AbstractCoreComponent<T> {
    fun inject(target: T)
}

interface BaseActivityComponent<T : Activity> : AbstractCoreComponent<T>

interface BaseFragmentComponent<T : Fragment> : AbstractCoreComponent<T>

interface BaseServiceComponent<T : Service> : AbstractCoreComponent<T>