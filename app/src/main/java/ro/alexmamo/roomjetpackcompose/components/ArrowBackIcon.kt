package ro.alexmamo.roomjetpackcompose.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun ArrowBackIcon(
    onArrowBackIconClick: () -> Unit
) {
    IconButton(
        onClick = onArrowBackIconClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null,
        )
    }
}