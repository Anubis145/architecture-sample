package com.example.data.home.mapper

import com.example.core.network.model.searchBooks.Rating

fun Rating.toRating(): com.example.domain.home.model.Rating {
    return com.example.domain.home.model.Rating(
        average = average,
    )
}
