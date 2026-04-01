package com.example.data.home.mapper

import com.example.core.network.model.searchBooks.SearchBooksRs
import com.example.domain.home.model.SearchBooks

fun SearchBooksRs.toSearchBooks() : SearchBooks {
    return SearchBooks(
        available = available,
        number = number,
        offset = offset,
        books = books.flatten().map { it.toBook() }
    )
}
