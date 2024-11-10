package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.AuthorText
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.TitleText

@Composable
fun BookDetailsContent(
    innerPadding: PaddingValues,
    book: Book
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(8.dp)
    ) {
        TitleText(
            title = book.title
        )
        AuthorText(
            author = book.author
        )
    }
}