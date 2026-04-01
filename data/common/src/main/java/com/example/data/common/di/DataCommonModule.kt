package com.example.data.common.di

import com.example.data.common.networkHelper.NetworkHelper
import com.example.data.common.networkHelper.NetworkHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataCommonModule {

    @Binds
    @Singleton
    abstract fun bindNetworkHelper(networkHelperImpl: NetworkHelperImpl) : NetworkHelper
}
