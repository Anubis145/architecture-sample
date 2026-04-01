package com.example.core.network.di

import com.example.core.network.api.BooksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

    @Provides
    @Singleton
    fun provideBooksApi(@Books retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }
}