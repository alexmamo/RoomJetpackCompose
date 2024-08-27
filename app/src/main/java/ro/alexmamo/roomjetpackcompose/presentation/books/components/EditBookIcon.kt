package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EDIT_BOOK

@Composable
fun EditBookIcon(
    editBook: () -> Unit
) {
    IconButton(
        onClick = editBook
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = EDIT_BOOK,
        )
    }
}