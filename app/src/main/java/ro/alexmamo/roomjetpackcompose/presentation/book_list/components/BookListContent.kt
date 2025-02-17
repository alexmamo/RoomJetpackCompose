package ro.alexmamo.roomjetpackcompose.presentation.book_list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.BookError

const val NON_EXISTENT_BOOK_ID = -1

@Composable
fun BookListContent(
    innerPadding: PaddingValues,
    bookList: List<Book>,
    onBookCardClick: (Book) -> Unit,
    onUpdateBook: (Book) -> Unit,
    onUpdateBookError: (BookError) -> Unit,
    onDeleteBook: (Book) -> Unit,
    onNoUpdates: () -> Unit
) {
    var editBookId by remember { mutableIntStateOf(NON_EXISTENT_BOOK_ID) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(innerPadding)
    ) {
        items(
            items = bookList,
            key = { book ->
                book.id
            }
        ) { book ->
            if (editBookId != book.id) {
                BookCard(
                    book = book,
                    onBookCardClick = {
                        onBookCardClick(book)
                    },
                    onEditBook = {
                        editBookId = book.id
                    },
                    onDeleteBook = {
                        onDeleteBook(book)
                        editBookId = NON_EXISTENT_BOOK_ID
                    }
                )
            } else {
                EditableBookCard(
                    book = book,
                    onUpdateBook = { updatedBook ->
                        updatedBook.apply {
                            if (title.isEmpty()) {
                                onUpdateBookError(BookError.EmptyTitle)
                            } else if (author.isEmpty()) {
                                onUpdateBookError(BookError.EmptyAuthor)
                            } else {
                                if (updatedBook != book) {
                                    onUpdateBook(updatedBook)
                                } else {
                                    onNoUpdates()
                                }
                                editBookId = NON_EXISTENT_BOOK_ID
                            }
                        }
                    },
                    onCancel = {
                        editBookId = NON_EXISTENT_BOOK_ID
                    }
                )
            }
        }
    }
}