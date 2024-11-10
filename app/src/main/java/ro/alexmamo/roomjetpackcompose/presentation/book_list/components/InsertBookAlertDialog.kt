package ro.alexmamo.roomjetpackcompose.presentation.book_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.components.ActionButton
import ro.alexmamo.roomjetpackcompose.domain.model.Book

const val EMPTY_STRING = ""

@Composable
fun InsertBookAlertDialog(
    onEmptyTitleInsert: () -> Unit,
    onEmptyAuthorInsert: () -> Unit,
    onInsertBook: (book: Book) -> Unit,
    onCancel: () -> Unit,
) {
    var title by remember { mutableStateOf(EMPTY_STRING) }
    var author by remember { mutableStateOf(EMPTY_STRING) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(
                text = stringResource(
                    id = R.string.insert_book
                )
            )
        },
        text = {
            Column {
                TitleTextField(
                    title = title,
                    onUpdateTitle = { newTitle ->
                        title = newTitle
                    }
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                AuthorTextField(
                    author = author,
                    onUpdateAuthor = { newAuthor ->
                        author = newAuthor
                    }
                )
            }
        },
        confirmButton = {
            ActionButton(
                onActionButtonClick = {
                    if (title.isEmpty()) {
                        onEmptyTitleInsert()
                        return@ActionButton
                    }
                    if (author.isEmpty()) {
                        onEmptyAuthorInsert()
                        return@ActionButton
                    }
                    onInsertBook(Book(
                        id = 0,
                        title = title,
                        author = author
                    ))
                    onCancel()
                },
                resourceId = R.string.insert_button
            )
        },
        dismissButton = {
            ActionButton(
                onActionButtonClick = onCancel,
                resourceId = R.string.cancel_button
            )
        }
    )
}