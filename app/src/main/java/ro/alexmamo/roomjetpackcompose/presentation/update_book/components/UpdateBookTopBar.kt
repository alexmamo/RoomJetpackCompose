package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BACK
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE_BOOK_SCREEN

@Composable
fun UpdateBookTopBar(
    navController: NavController
) {
    TopAppBar (
        title = {
            Text(
                text = UPDATE_BOOK_SCREEN
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = BACK,
                )
            }
        }
    )
}