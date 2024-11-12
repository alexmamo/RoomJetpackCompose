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

const val NON_EXISTENT_BOOK_ID = -1

@Composable
fun BookListContent(
    innerPadding: PaddingValues,
    bookList: List<Book>,
    onBookCardClick: (Book) -> Unit,
    onUpdateBook: (Book) -> Unit,
    onEmptyTitleUpdate: () -> Unit,
    onEmptyAuthorUpdate: () -> Unit,
    onNoUpdates: () -> Unit,
    onDeleteBook: (Book) -> Unit
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
                    }
                )
            } else {
                EditableBookCard(
                    book = book,
                    onUpdateBook = { updatedBook ->
                        updatedBook.apply {
                            if (title.isEmpty()) {
                                onEmptyTitleUpdate()
                            } else if (author.isEmpty()) {
                                onEmptyAuthorUpdate()
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