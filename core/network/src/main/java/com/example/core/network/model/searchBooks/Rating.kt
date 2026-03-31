package com.example.core.network.model.searchBooks

import kotlinx.serialization.SerialName

data class Rating(
    @SerialName("average")
    val average: Double,
)
