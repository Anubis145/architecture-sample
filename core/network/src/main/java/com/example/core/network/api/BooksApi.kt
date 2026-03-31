package com.example.core.network.api

import com.example.core.network.model.ApiResult
import com.example.core.network.model.searchBooks.SearchBooksRs
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("/search-books")
    suspend fun searchBooks(
        @Query("query")
        query: String
    ) : ApiResult<SearchBooksRs>
}
