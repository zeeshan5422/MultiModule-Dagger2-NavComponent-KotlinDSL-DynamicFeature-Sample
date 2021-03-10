package com.nfs.ascent.nfslogin.di

import com.nfs.ascent.base.coreComponent
import com.nfs.ascent.nfslogin.ui.LoginFragment
import com.nfs.ascent.nfslogin.ui.LoginActivity

fun LoginActivity.inject() {
    DaggerLoginComponent.builder()
        .context(this)
        .coreComponent(coreComponent())
        .build()
        .inject(this)
}

fun LoginFragment.inject() {
    DaggerLoginComponent.builder()
        .context(requireContext())
        .coreComponent(coreComponent())
        .build()
        .inject(this)
}
