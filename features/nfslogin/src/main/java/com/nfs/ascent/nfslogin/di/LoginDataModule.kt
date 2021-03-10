package com.nfs.ascent.nfslogin.di

import com.nfs.ascent.core.di.scope.FeatureScope
import com.nfs.ascent.nfslogin.data.repository.LoginRepository
import com.nfs.ascent.nfslogin.data.repository.LoginRepositoryImpl
import com.nfs.ascent.nfslogin.data.source.*
import dagger.Binds
import dagger.Module

@Module
interface LoginDataModule {

    @Binds
    @FeatureScope
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @FeatureScope
    fun bindLoginLocalDataSource(loginDataSourceImpl: LocalLoginDataSourceImpl): LocalLoginDataSource

    @Binds
    @FeatureScope
    fun bindLoginRemoteDataSource(loginDataSourceImpl: RemoteLoginDataSourceImpl): RemoteLoginDataSource

//    @Binds
//    @FeatureScope
//    fun bindLoginUseCase(loginUseCase: LoginUseCase): RemoteLoginDataSource

}