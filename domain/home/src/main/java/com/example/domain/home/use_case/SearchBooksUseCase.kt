package com.example.domain.home.use_case

import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import com.example.domain.home.model.SearchBooks
import com.example.domain.home.repository.SearchBooksRepository
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val searchBooksRepository: SearchBooksRepository,
) {
    suspend operator fun invoke(query: String) : ArchResult<SearchBooks, DataError.NetworkError> {
        return searchBooksRepository.searchBooks(query)
    }
}
