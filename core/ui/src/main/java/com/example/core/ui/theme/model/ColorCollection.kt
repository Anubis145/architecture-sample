package com.example.core.ui.theme.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

typealias LightTheme = ColorCollection

@Immutable
data class ColorCollection(
    val white: Color = Color.White,
    val textColor: Color = Color.Black,
    val errorRed: Color = Color.Red,
    val warningBlue: Color = Color.Blue
)
