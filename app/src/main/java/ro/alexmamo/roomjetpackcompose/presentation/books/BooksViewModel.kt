package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.NO_VALUE
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    var book by mutableStateOf(Book(0, NO_VALUE, NO_VALUE))
        private set
    var openDialog by mutableStateOf(false)

    val books = repo.getBooksFromRoom()

    fun getBook(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        book = repo.getBookFromRoom(id)
    }

    fun addBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repo.addBookToRoom(book)
    }

    fun updateBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateBookInRoom(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteBookFromRoom(book)
    }

    fun updateTitle(title: String) {
        book = book.copy(
            title = title
        )
    }

    fun updateAuthor(author: String) {
        book = book.copy(
            author = author
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}