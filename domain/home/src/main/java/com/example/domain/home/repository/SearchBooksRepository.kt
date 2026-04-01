package com.example.domain.home.repository

import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import com.example.domain.home.model.SearchBooks

interface SearchBooksRepository {
    suspend fun searchBooks(query: String): ArchResult<SearchBooks, DataError.NetworkError>
}