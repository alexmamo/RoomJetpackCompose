package ro.alexmamo.roomjetpackcompose.presentation.book_list.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun BookListTopBar() {
    TopAppBar (
        title = {
            Text(
                text = stringResource(
                    id = R.string.book_list_screen_title
                )
            )
        }
    )
}