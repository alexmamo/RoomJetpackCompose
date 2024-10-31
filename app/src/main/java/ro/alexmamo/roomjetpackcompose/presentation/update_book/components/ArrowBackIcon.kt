package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.roomjetpackcompose.R

@Composable
fun ArrowBackIcon(
    onArrowBackIconClick: () -> Unit
) {
    IconButton(
        onClick = onArrowBackIconClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(
                id = R.string.navigate_back
            )
        )
    }
}