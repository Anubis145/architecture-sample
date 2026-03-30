package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer

data class HomeState(
    val isLoading: Boolean = false,
) : Reducer.State
