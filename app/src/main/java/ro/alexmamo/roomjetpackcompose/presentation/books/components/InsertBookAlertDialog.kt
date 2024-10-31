package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.domain.model.Book

const val EMPTY_STRING = ""

@Composable
fun InsertBookAlertDialog(
    showEmptyTitleMessage: () -> Unit,
    showEmptyAuthorMessage: () -> Unit,
    insertBook: (book: Book) -> Unit,
    closeDialog: () -> Unit,
) {
    var title by remember { mutableStateOf(EMPTY_STRING) }
    var author by remember { mutableStateOf(EMPTY_STRING) }
    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        title = {
            Text(
                text = stringResource(
                    id = R.string.insert_book
                )
            )
        },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { newTitle ->
                        title = newTitle
                    },
                    placeholder = {
                        Text(
                            text = stringResource(
                                id = R.string.book_title
                            )
                        )
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                TextField(
                    value = author,
                    onValueChange = { newAuthor ->
                        author = newAuthor
                    },
                    placeholder = {
                        Text(
                            text = stringResource(
                                id = R.string.author_name
                            )
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isEmpty()) {
                        showEmptyTitleMessage()
                        return@TextButton
                    }
                    if (author.isEmpty()) {
                        showEmptyAuthorMessage()
                        return@TextButton
                    }
                    insertBook(Book(
                        id = 0,
                        title = title,
                        author = author
                    ))
                    closeDialog()
                }
            ) {
                Text(
                    text = stringResource(
                        id = R.string.insert_button
                    )
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = closeDialog
            ) {
                Text(
                    text = stringResource(
                        id = R.string.dismiss_button
                    )
                )
            }
        }
    )
}