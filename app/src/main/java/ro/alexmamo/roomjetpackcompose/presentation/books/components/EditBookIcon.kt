package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun EditBookIcon(
    onEditBookIconClick: () -> Unit
) {
    IconButton(
        onClick = onEditBookIconClick
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = stringResource(
                id = R.string.update_book
            )
        )
    }
}