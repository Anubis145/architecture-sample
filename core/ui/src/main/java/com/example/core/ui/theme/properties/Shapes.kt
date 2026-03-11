package com.example.core.ui.theme.properties

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class ArchShapes(
    val xs: CornerBasedShape = RoundedCornerShape(4.dp),
    val s: CornerBasedShape = RoundedCornerShape(8.dp),
    val m: CornerBasedShape = RoundedCornerShape(12.dp),
    val l: CornerBasedShape = RoundedCornerShape(16.dp),
    val xl: CornerBasedShape = RoundedCornerShape(24.dp),

    val button: CornerBasedShape = RoundedCornerShape(24.dp),
    val snackbar: CornerBasedShape = RoundedCornerShape(12.dp)
)

val DefaultShapes = ArchShapes()
