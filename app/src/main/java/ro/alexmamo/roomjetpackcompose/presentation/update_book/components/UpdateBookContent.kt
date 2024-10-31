package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun UpdateBookContent(
    padding: PaddingValues,
    book: Book,
    showEmptyTitleMessage: () -> Unit,
    showEmptyAuthorMessage: () -> Unit,
    updateBook: (Book) -> Unit,
    showNoUpdatesMessage: () -> Unit,
    navigateBack: () -> Unit
) {
    var updatedBook by remember { mutableStateOf(book) }

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = updatedBook.title,
            onValueChange = { newTitle ->
                updatedBook = updatedBook.copy(
                    title = newTitle
                )
            },
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.book_title
                    )
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = updatedBook.author,
            onValueChange = { newAuthor ->
                updatedBook = updatedBook.copy(
                    author = newAuthor
                )
            },
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.author_name
                    )
                )
            }
        )
        Button(
            onClick = {
                if (updatedBook.title.isEmpty()) {
                    showEmptyTitleMessage()
                    return@Button
                }
                if (updatedBook.author.isEmpty()) {
                    showEmptyAuthorMessage()
                    return@Button
                }
                if (updatedBook != book) {
                    updateBook(updatedBook)
                } else {
                    showNoUpdatesMessage()
                }
                navigateBack()
            }
        ) {
            Text(
                text = stringResource(
                    id = R.string.update_button
                )
            )
        }
    }
}