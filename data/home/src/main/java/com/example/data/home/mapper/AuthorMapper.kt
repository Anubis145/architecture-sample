package com.example.data.home.mapper

import com.example.core.network.model.searchBooks.Author

fun Author.toAuthor(): com.example.domain.home.model.Author {
    return com.example.domain.home.model.Author(
        id = id,
        name = name
    )
}
