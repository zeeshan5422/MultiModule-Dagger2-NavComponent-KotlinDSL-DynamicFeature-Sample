package com.nfs.ascent.nfslogin.di

import android.content.Context
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.BaseActivityComponent
import com.nfs.ascent.core.di.CoreComponent
import com.nfs.ascent.core.di.module.ActivityCommonModule
import com.nfs.ascent.core.di.scope.FeatureScope
import com.nfs.ascent.nfslogin.ui.LoginFragment
import com.nfs.ascent.nfslogin.ui.LoginActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(
    dependencies = [CoreComponent::class],
    modules = [LoginActivityModule::class,
        ActivityCommonModule::class, LoginNetworkModule::class,
        LoginDataModule::class, LoginDatabaseModule::class]
)
@FeatureScope
interface LoginComponent : BaseActivityComponent<LoginActivity> {

    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {
        fun context(@Named(ACTIVITY_CONTEXT) @BindsInstance context: Context): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): LoginComponent
    }

}