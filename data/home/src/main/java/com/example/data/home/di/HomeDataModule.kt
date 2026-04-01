package com.example.data.home.di

import com.example.data.home.repository.SearchBooksRepositoryImpl
import com.example.data.home.service.SearchBooksService
import com.example.data.home.service.SearchBooksServiceImpl
import com.example.domain.home.repository.SearchBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HomeDataModule {

    @Binds
    @Singleton
    abstract fun bindSearchBooksService(impl: SearchBooksServiceImpl): SearchBooksService

    @Binds
    @Singleton
    abstract fun bindSearchBooksRepository(impl: SearchBooksRepositoryImpl): SearchBooksRepository
}
