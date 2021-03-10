package com.nfs.ascent.base.di

import android.content.Context
import com.nfs.ascent.base.ui.MainActivity
import com.nfs.ascent.base.ui.MainFragment
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.BaseActivityComponent
import com.nfs.ascent.core.di.CoreComponent
import com.nfs.ascent.core.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(
    dependencies = [CoreComponent::class],
    modules = [MainActivityModule::class]
)
@FeatureScope
interface MainComponent : BaseActivityComponent<MainActivity> {

    fun inject(loginFragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun context(@Named(ACTIVITY_CONTEXT) @BindsInstance context: Context): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): MainComponent
    }

}