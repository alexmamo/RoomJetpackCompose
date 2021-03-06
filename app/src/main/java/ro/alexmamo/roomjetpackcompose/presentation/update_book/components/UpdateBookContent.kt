package ro.alexmamo.roomjetpackcompose.presentation.update_book.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.AUTHOR
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.TITLE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksViewModel

@Composable
fun UpdateBookContent(
    padding: PaddingValues,
    bookId: Int,
    navigateToBooksScreen: () -> Unit,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val title = viewModel.book.title
    val author = viewModel.book.author

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = title,
            onValueChange = { title -> viewModel.updateTitle(title) },
            placeholder = {
                Text(
                    text = TITLE
                )
            }
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        TextField(
            value = author,
            onValueChange = { author ->
                viewModel.updateAuthor(author)
            },
            placeholder = {
                Text(
                    text = AUTHOR
                )
            }
        )
        Button(
            onClick = {
                val updatedBook = Book(bookId, title, author)
                viewModel.updateBook(updatedBook)
                navigateToBooksScreen()
            }
        ) {
            Text(
                text = UPDATE
            )
        }
    }
}