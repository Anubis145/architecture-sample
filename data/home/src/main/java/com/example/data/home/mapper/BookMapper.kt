package com.example.data.home.mapper

import com.example.core.network.model.searchBooks.Book

fun Book.toBook(): com.example.domain.home.model.Book {
    return com.example.domain.home.model.Book(
        id = id,
        title = title,
        subtitle = subtitle.toString(),
        image = image.toString(),
        authors = authors.map { it.toAuthor() },
        rating = rating.toRating()
    )
}
