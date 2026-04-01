package com.example.core.network.di.books

import com.example.core.common.buildConfig.ArchBuildConfigProvider
import com.example.core.network.callAdapter.ApiResultCallAdapterFactory
import com.example.core.network.di.Books
import com.example.core.network.interceptors.BooksApiAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BooksApiModule {

    @Provides
    @Singleton
    @Books
    fun provideBooksApiOkhttpClient(
        baseOkHttpBuilder: OkHttpClient.Builder,
        booksApiAuthInterceptor: BooksApiAuthInterceptor,
    ): OkHttpClient {
        return baseOkHttpBuilder
            .addInterceptor(booksApiAuthInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Books
    fun provideBooksApiRetrofit(
        @Books okHttpClient: OkHttpClient,
        json: Json,
        archBuildConfigProvider: ArchBuildConfigProvider
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(archBuildConfigProvider.getBuildConfig().booksApiDevBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResultCallAdapterFactory.create(json))
            .build()
    }
}
