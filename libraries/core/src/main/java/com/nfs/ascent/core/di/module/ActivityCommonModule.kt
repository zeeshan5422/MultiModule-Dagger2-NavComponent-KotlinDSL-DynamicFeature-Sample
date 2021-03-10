package com.nfs.ascent.core.di.module

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import com.nfs.ascent.core.di.scope.FeatureScope
import dagger.Module
import dagger.Provides


@Module
class ActivityCommonModule {

    @Provides
    @FeatureScope
    fun provideRecources(activity: Activity): Resources = activity.resources
}
