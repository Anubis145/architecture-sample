package com.example.core.ui.effects.snackbar

data class SnackbarEvent(
    val type: SnackbarType,
    val message: String
) {
    enum class SnackbarType {
        WARNING,
        ERROR
    }
}
