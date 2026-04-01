package com.example.data.home.service

import com.example.core.network.api.BooksApi
import com.example.data.common.networkHelper.NetworkHelper
import com.example.data.home.mapper.toSearchBooks
import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import com.example.domain.home.model.SearchBooks
import javax.inject.Inject

class SearchBooksServiceImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val booksApi: BooksApi,
) : SearchBooksService {
    override suspend fun searchBooks(query: String): ArchResult<SearchBooks, DataError.NetworkError> {
        return networkHelper.fetchResult(
            mapper = { it.toSearchBooks() },
            apiCall = suspend { booksApi.searchBooks(query) }
        )
    }
}
