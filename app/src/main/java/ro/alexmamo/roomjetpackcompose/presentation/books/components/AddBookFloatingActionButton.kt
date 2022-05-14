package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.ADD_BOOK
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksViewModel

@Composable
fun AddBookFloatingActionButton(
    viewModel: BooksViewModel = hiltViewModel()
) {
    FloatingActionButton(
        onClick = {
            viewModel.openDialog = true
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ADD_BOOK
        )
    }
}