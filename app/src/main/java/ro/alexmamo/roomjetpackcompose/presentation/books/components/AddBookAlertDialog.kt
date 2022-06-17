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
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.ADD
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.ADD_BOOK
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.AUTHOR
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.DISMISS
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.TITLE
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun AddBookAlertDialog(
    closeDialog: () -> Unit,
    addBook: (book: Book) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = {
            closeDialog()
        },
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
                            text = TITLE
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
                    text = ADD
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    closeDialog()
                }
            ) {
                Text(
                    text = DISMISS
                )
            }
        }
    )
}