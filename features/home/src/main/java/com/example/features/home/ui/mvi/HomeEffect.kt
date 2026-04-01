package com.example.features.home.ui.mvi

import com.example.core.common.mvi.Reducer

sealed interface HomeEffect : Reducer.Effect {
    object ShowNoInternetSnackbar : HomeEffect
    object ShowSomethingWentWrongSnackbar : HomeEffect
}
