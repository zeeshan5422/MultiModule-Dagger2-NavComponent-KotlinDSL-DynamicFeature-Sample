package com.nfs.ascent.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfs.ascent.base.vm.MainActivityViewModel
import com.nfs.ascent.base.vm.MainFragmentViewModel
import com.nfs.ascent.core.di.ActivityViewModelFactory
import com.nfs.ascent.core.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    internal abstract fun bindsActivityViewModelFactory(factoryActivity: ActivityViewModelFactory): ViewModelProvider.Factory

    // Add any other ViewModel that you may have
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindsMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    internal abstract fun bindsMainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel
}