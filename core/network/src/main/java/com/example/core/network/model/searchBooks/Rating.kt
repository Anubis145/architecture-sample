package com.example.core.network.model.searchBooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("average")
    val average: Double,
)
