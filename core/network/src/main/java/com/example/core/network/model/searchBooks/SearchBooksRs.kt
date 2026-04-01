package com.example.core.network.model.searchBooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchBooksRs(
    @SerialName("available")
    val available: Int,
    @SerialName("number")
    val number: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("books")
    val books: List<Book>
)
