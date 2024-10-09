package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import ro.alexmamo.roomjetpackcompose.components.ArrowBackIcon
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE_BOOK_SCREEN

@Composable
fun UpdateBookTopBar(
    onArrowBackIconClick: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = UPDATE_BOOK_SCREEN
            )
        },
        navigationIcon = {
            ArrowBackIcon(
                onArrowBackIconClick = onArrowBackIconClick
            )
        }
    )
}