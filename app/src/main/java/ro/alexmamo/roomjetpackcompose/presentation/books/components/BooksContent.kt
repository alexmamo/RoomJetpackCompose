package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Composable
fun BooksContent(
    padding: PaddingValues,
    books: List<Book>,
    deleteBook: (Book) -> Unit,
    navigateToUpdateBookScreen: (Book) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = books,
            key = { book ->
                book.id
            }
        ) { book ->
            BookCard(
                book = book,
                onDeleteBookIconClick = {
                    deleteBook(book)
                },
                onEditBookIconClick = {
                    navigateToUpdateBookScreen(book)
                }
            )
        }
    }
}