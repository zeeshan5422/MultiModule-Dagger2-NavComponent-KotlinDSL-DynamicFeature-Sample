package com.nfs.ascent.base.di

import com.nfs.ascent.base.coreComponent
import com.nfs.ascent.base.ui.MainActivity
import com.nfs.ascent.base.ui.MainFragment

fun MainActivity.inject() {
    DaggerMainComponent.builder()
        .context(this)
        .coreComponent(coreComponent())
        .build()
        .inject(this)
}

    fun MainFragment.inject() {
        DaggerMainComponent.builder()
            .context(requireContext())
            .coreComponent(coreComponent())
            .build()
            .inject(this)
}