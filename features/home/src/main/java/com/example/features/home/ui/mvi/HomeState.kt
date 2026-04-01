package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer
import com.example.domain.home.model.Book

data class HomeState(
    val isLoading: Boolean = false,
    val searchValue: String = "",
    val books: List<Book> = emptyList()
) : Reducer.State
