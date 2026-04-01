package com.example.domain.home.model

data class SearchBooks(
    val available: Int,
    val number: Int,
    val offset: Int,
    val books: List<Book>
)
