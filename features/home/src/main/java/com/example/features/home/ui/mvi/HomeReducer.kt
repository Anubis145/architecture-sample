package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer
import javax.inject.Inject

class HomeReducer @Inject constructor(

) : Reducer<HomeState, HomeEvent, HomeEffect> {

    override fun reduce(
        previousState: HomeState,
        event: HomeEvent
    ): Pair<HomeState, HomeEffect?> {
        return when (event) {
            is HomeEvent.SearchValueChanged -> {
                previousState.copy(searchValue = event.value) to null
            }
        }
    }
}