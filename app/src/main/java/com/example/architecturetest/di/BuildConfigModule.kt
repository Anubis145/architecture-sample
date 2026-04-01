package com.example.architecturetest.di

import com.example.architecturetest.BuildConfig
import com.example.core.common.buildConfig.ArchBuildConfig
import com.example.core.common.buildConfig.ArchBuildConfigProvider
import com.example.core.common.buildConfig.BuildType
import com.example.core.common.buildConfig.ProductFlavor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
abstract class BuildConfigModule {

    @Binds
    abstract fun provideBuildConfig(archBuildConfigProviderImpl: ArchBuildConfigProviderImpl) : ArchBuildConfigProvider
}

class ArchBuildConfigProviderImpl @Inject constructor() : ArchBuildConfigProvider {
    override fun getBuildConfig(): ArchBuildConfig {
        return ArchBuildConfig(
            debug = BuildConfig.DEBUG,
            applicationId = BuildConfig.APPLICATION_ID,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME,
            productFlavor = ProductFlavor.fromString(BuildConfig.FLAVOR),
            buildType = BuildType.fromString(BuildConfig.BUILD_TYPE),
            booksApiDevBaseUrl = BuildConfig.BOOKS_API_BASE_URL_DEV,
            booksApiKey = BuildConfig.BOOKS_API_KEY
        )
    }
}
