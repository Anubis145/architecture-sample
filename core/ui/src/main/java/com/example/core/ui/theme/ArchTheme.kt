package com.example.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.core.ui.theme.model.ColorCollection
import com.example.core.ui.theme.properties.ArchDimens
import com.example.core.ui.theme.properties.ArchShapes
import com.example.core.ui.theme.properties.ArchTypography
import com.example.core.ui.theme.properties.DefaultShapes
import com.example.core.ui.theme.properties.DefaultDimens
import com.example.core.ui.theme.properties.DefaultTypography
import com.example.core.ui.theme.properties.defaultLightColors

internal val LocalColors = staticCompositionLocalOf { ColorCollection() }
internal val LocalDimens = staticCompositionLocalOf { DefaultDimens }
internal val LocalTypography = staticCompositionLocalOf { DefaultTypography }
internal val LocalShapes = staticCompositionLocalOf { DefaultShapes }

@Composable
fun ArchTheme(
    dynamicTheme: ColorCollection? = null,
    content: @Composable () -> Unit,
) {
    val colorScheme = dynamicTheme ?: defaultLightColors

    MaterialTheme {
        CompositionLocalProvider(
            LocalColors provides colorScheme,
            LocalShapes provides DefaultShapes,
            LocalDimens provides DefaultDimens,
            LocalTypography provides DefaultTypography,
            content = content
        )
    }
}

object ArchTheme {
    val typography: ArchTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val colors: ColorCollection
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val dimens: ArchDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
    val shapes: ArchShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}
