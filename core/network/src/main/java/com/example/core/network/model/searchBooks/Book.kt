package com.example.core.network.model.searchBooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("image")
    val image: String,
    @SerialName("authors")
    val authors: List<Author>,
    @SerialName("rating")
    val rating: Rating
)
