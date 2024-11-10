package ro.alexmamo.roomjetpackcompose.presentation.book_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.components.ActionIconButton
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun BookCard(
    book: Book,
    onBookCardClick: () -> Unit,
    onEditBook: () -> Unit,
    onDeleteBook: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            start = 8.dp,
            top = 4.dp,
            end = 8.dp,
            bottom = 4.dp
        ).clickable {
            onBookCardClick()
        },
        shape = MaterialTheme.shapes.small,
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column {
                TitleText(
                    title = book.title
                )
                AuthorText(
                    author = book.author
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            ActionIconButton(
                onActionIconButtonClick = onEditBook,
                imageVector = Icons.Default.Edit,
                resourceId = R.string.edit_icon
            )
            ActionIconButton(
                onActionIconButtonClick = onDeleteBook,
                imageVector = Icons.Default.Delete,
                resourceId = R.string.delete_icon
            )
        }
    }
}