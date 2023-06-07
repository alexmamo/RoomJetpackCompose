package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.ADD_BUTTON
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.ADD_BOOK
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.AUTHOR
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_TITLE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.DISMISS_BUTTON
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_STRING
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun AddBookAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addBook: (book: Book) -> Unit
) {
    if (openDialog) {
        var title by remember { mutableStateOf(EMPTY_STRING) }
        var author by remember { mutableStateOf(EMPTY_STRING) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = ADD_BOOK
                )
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(
                                text = BOOK_TITLE
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
                        onValueChange = { author = it },
                        placeholder = {
                            Text(
                                text = AUTHOR
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        val book = Book(0, title, author)
                        addBook(book)
                    }
                ) {
                    Text(
                        text = ADD_BUTTON
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text(
                        text = DISMISS_BUTTON
                    )
                }
            }
        )
    }
}