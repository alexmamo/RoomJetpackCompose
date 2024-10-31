package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun UpdateBookTopBar(
    onArrowBackIconClick: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = stringResource(
                    id = R.string.update_book_screen_title
                )
            )
        },
        navigationIcon = {
            ArrowBackIcon(
                onArrowBackIconClick = onArrowBackIconClick
            )
        }
    )
}