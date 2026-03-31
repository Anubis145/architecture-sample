package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer

sealed interface HomeEvent : Reducer.Event {
    class SearchValueChanged(val value: String) : HomeEvent
}
