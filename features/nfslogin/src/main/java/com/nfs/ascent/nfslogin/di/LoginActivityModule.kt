package com.nfs.ascent.nfslogin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfs.ascent.core.di.ActivityViewModelFactory
import com.nfs.ascent.core.di.scope.ViewModelKey
import com.nfs.ascent.nfslogin.vm.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginActivityModule {

    @Binds
    internal abstract fun bindsActivityViewModelFactory(factoryActivity: ActivityViewModelFactory): ViewModelProvider.Factory

    // Add any other ViewModel that you may have
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindsLoginViewModel(viewModel: LoginViewModel): ViewModel
}