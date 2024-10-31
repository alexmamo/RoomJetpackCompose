package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun DeleteBookIcon(
    onDeleteBookIconClick: () -> Unit
) {
    IconButton(
        onClick = onDeleteBookIconClick
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(
                id = R.string.delete_book
            )
        )
    }
}