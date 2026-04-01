package com.example.domain.home.model

data class Book(
    val id: Long,
    val title: String,
    val subtitle: String,
    val image: String,
    val authors: List<Author>,
    val rating: Rating
)
