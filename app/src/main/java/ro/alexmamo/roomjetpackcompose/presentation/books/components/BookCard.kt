package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.DELETE_BOOK
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
@ExperimentalMaterialApi
fun BookCard(
    book: Book,
    deleteBook: () -> Unit,
    navigateToUpdateBookScreen: (bookId: Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
        onClick = {
            navigateToUpdateBookScreen(book.id)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.90f)
            ){
                Text(
                    text = book.title,
                    color = Color.DarkGray,
                    fontSize = 25.sp
                )
                Text(
                    text = "by ${book.author}",
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            IconButton(
                onClick = deleteBook
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = DELETE_BOOK,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}