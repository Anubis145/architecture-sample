package com.example.core.ui.theme.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

typealias LightTheme = ColorCollection

@Immutable
data class ColorCollection(
    val textColor: Color = Color.Black
)
