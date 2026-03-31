package com.example.core.ui.components.inputField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.ui.components.icon.ArchIcon
import com.example.core.ui.theme.ArchTheme

@Composable
fun SearchInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    onClick: () -> Unit = {},
    height: Dp = 56.dp,
    backgroundColor: Color = ArchTheme.colors.white,
    borderColor: Color = ArchTheme.colors.inputFieldGray,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = backgroundColor,
                shape = ArchTheme.shapes.searchInputField
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = ArchTheme.shapes.searchInputField
            )
            .clickable(onClick = onClick),
        value = value,
        onValueChange = onValueChange,
        label = {
            Box {
                Text(text = hint)
            }
        },
        singleLine = true,
        shape = ArchTheme.shapes.searchInputField,
        leadingIcon = {
            ArchIcon(
                painter = painterResource(android.R.drawable.ic_menu_search),
            )
        },
    )
}

@Composable
@Preview(showBackground = true)
private fun SearchInputFieldPreview() {
    SearchInputField(
        value = "Value",
        hint = "Hint",
        onValueChange = {},
        backgroundColor = Color.Blue,
    )
}
