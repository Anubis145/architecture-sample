package com.example.features.home.ui.compose

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.ui.components.spacer.Spacer
import com.example.core.ui.theme.ArchTheme
import com.example.domain.home.model.Book
import com.example.domain.home.model.Rating

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    book: Book,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ArchTheme.colors.gray)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = book.image,
            contentDescription = null,
            placeholder = painterResource(android.R.drawable.stat_notify_sync)
        )
        Spacer(16.dp)
        Column {
            Text(
                text = book.title,
            )
            Spacer(4.dp)
            Text(
                text = book.subtitle,
            )
        }
    }
}

@Preview
@Composable
fun BookItemPreview() {
    BookItem(
        book = Book(
            id = 1,
            title = "Title",
            subtitle = "Subtitle",
            image = "https://someimage",
            authors = listOf(),
            rating = Rating(4.4)
        )
    )
}
