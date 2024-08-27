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
    showEmptyTitleMessage: () -> Unit,
    updateBookTitle: (title: String) -> Unit,
    showEmptyAuthorMessage: () -> Unit,
    updateBookAuthor: (author: String) -> Unit,
    updateBook: () -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = book.title,
            onValueChange = { newTitle ->
                updateBookTitle(newTitle)
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
            onValueChange = { newAuthor ->
                updateBookAuthor(newAuthor)
            },
            placeholder = {
                Text(
                    text = AUTHOR
                )
            }
        )
        Button(
            onClick = {
                if (book.title.isEmpty()) {
                    showEmptyTitleMessage()
                    return@Button
                }
                if (book.author.isEmpty()) {
                    showEmptyAuthorMessage()
                    return@Button
                }
                updateBook()
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE_BUTTON
            )
        }
    }
}