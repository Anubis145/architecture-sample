package com.example.features.home.mvi

import com.example.core.common.mvi.Reducer

data class HomeState(
    val isLoading: Boolean = false,
) : Reducer.State
