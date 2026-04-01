package com.example.data.home.service

import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import com.example.domain.home.model.SearchBooks

interface SearchBooksService {
    suspend fun searchBooks(query: String) : ArchResult<SearchBooks, DataError.NetworkError>
}