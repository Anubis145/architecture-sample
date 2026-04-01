package com.example.core.ui.effects.snackbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SnackbarViewModel @Inject constructor() : ViewModel() {

    private val _snackbarFlow = MutableSharedFlow<SnackbarEvent>(extraBufferCapacity = 1)
    val snackbarFlow = _snackbarFlow.asSharedFlow()

    fun showSnackbar(event: SnackbarEvent) {
        _snackbarFlow.tryEmit(event)
    }
}
