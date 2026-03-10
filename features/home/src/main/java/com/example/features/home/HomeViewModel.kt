package com.example.features.home

import androidx.lifecycle.SavedStateHandle
import com.example.core.common.mvi.BaseViewModel
import com.example.features.home.mvi.HomeEffect
import com.example.features.home.mvi.HomeEvent
import com.example.features.home.mvi.HomeReducer
import com.example.features.home.mvi.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    reducer: HomeReducer,
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>(
    initialState = HomeState(),
    reducer = reducer
), HomeIntent {

}
