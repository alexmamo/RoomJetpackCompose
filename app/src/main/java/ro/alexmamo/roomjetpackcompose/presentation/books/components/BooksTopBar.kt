package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun BooksTopBar() {
    TopAppBar (
        title = {
            Text(
                text = stringResource(
                    id = R.string.books_screen_title
                )
            )
        }
    )
}