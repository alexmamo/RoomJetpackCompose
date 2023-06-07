package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.AUTHOR
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_TITLE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE_BUTTON
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun UpdateBookContent(
    padding: PaddingValues,
    book: Book,
    updateTitle: (title: String) -> Unit,
    updateAuthor: (author: String) -> Unit,
    updateBook: (book: Book) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = book.title,
            onValueChange = { title ->
                updateTitle(title)
            },
            placeholder = {
                Text(
                    text = BOOK_TITLE
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = book.author,
            onValueChange = { author ->
                updateAuthor(author)
            },
            placeholder = {
                Text(
                    text = AUTHOR
                )
            }
        )
        Button(
            onClick = {
                updateBook(book)
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE_BUTTON
            )
        }
    }
}