package com.example.data.home.repository

import com.example.data.home.service.SearchBooksService
import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import com.example.domain.home.model.SearchBooks
import com.example.domain.home.repository.SearchBooksRepository
import javax.inject.Inject

class SearchBooksRepositoryImpl @Inject constructor(
    private val searchBooksService: SearchBooksService
) : SearchBooksRepository {
    override suspend fun searchBooks(query: String): ArchResult<SearchBooks, DataError.NetworkError> {
        return searchBooksService.searchBooks(query)
    }
}