package com.example.core.ui.components.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ArchIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    tint: Color = LocalContentColor.current,
    sizeDp: Dp = 32.dp,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    if (onClick == null) {
        Icon(
            modifier = modifier
                .size(sizeDp),
            painter = painter,
            tint = tint,
            contentDescription = contentDescription
        )
    } else {
        Icon(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = onClick,
                )
                .then(modifier)
                .size(sizeDp),
            painter = painter,
            tint = tint,
            contentDescription = contentDescription
        )
    }
}
