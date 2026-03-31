package com.example.core.network.model.searchBooks

import kotlinx.serialization.SerialName

data class Author(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
)
