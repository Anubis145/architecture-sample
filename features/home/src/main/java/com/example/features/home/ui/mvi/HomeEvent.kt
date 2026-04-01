package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer
import com.example.domain.home.model.Book

sealed interface HomeEvent : Reducer.Event {
    class SearchValueChanged(val value: String) : HomeEvent
    class SearchResult(val books: List<Book>) : HomeEvent
    object SearchResultIsEmpty : HomeEvent
    object ShowLoading : HomeEvent
    object HideLoading : HomeEvent
}
