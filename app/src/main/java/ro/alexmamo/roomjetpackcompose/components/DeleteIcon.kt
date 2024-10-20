package ro.alexmamo.roomjetpackcompose.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.DELETE_ITEM

@Composable
fun DeleteIcon(
    onDeleteIconClick: () -> Unit
) {
    IconButton(
        onClick = onDeleteIconClick
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = DELETE_ITEM,
        )
    }
}