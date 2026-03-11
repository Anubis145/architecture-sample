package com.example.core.ui.components.snackbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.components.icon.ArchIcon
import com.example.core.ui.components.spacer.Spacer
import com.example.core.ui.theme.ArchTheme

@Composable
fun ArchSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
) {
    val visuals = snackbarData.visuals

    if (visuals is ArchSnackbarVisuals) {
        ArchSnackbarInternal(visuals, modifier)
    } else {
        Snackbar(snackbarData, modifier)
    }
}

@Composable
private fun ArchSnackbarInternal(
    visuals: ArchSnackbarVisuals,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (visuals) {
        is ArchSnackbarVisuals.Error -> ArchTheme.colors.errorRed
        is ArchSnackbarVisuals.Warning -> ArchTheme.colors.warningBlue
    }

    Surface(
        color = backgroundColor,
        contentColor = ArchTheme.colors.white,
        shape = ArchTheme.shapes.snackbar,
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ArchIcon(
                painter = painterResource(android.R.drawable.ic_dialog_alert),
                sizeDp = 24.dp
            )
            Spacer(16.dp)
            Text(text = visuals.message)
        }
    }
}

sealed class ArchSnackbarVisuals(
    override val message: String,
    override val actionLabel: String?,
    override val duration: SnackbarDuration,
    override val withDismissAction: Boolean
) : SnackbarVisuals {
    class Error(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        withDismissAction: Boolean = false
    ) : ArchSnackbarVisuals(message, actionLabel, duration, withDismissAction)

    class Warning(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        withDismissAction: Boolean = false
    ) : ArchSnackbarVisuals(message, actionLabel, duration, withDismissAction)
}

@Composable
@Preview(showBackground = true)
fun ArchSnackbarInternalPreview() {
    ArchTheme {
        ArchSnackbarInternal(
            ArchSnackbarVisuals.Warning(message = "Warning text")
        )
    }
}
