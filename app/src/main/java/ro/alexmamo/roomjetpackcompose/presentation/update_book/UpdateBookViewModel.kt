package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_STRING
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import javax.inject.Inject

@HiltViewModel
class UpdateBookViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    var book by mutableStateOf(Book(0, EMPTY_STRING, EMPTY_STRING))
        private set

    fun getBookById(id: Int) = viewModelScope.launch {
        book = repo.getBookById(
            id = id
        )
    }

    fun updateBookTitle(title: String) {
        book = book.copy(
            title = title
        )
    }

    fun updateBookAuthor(author: String) {
        book = book.copy(
            author = author
        )
    }

    fun updateBook() = viewModelScope.launch {
        repo.updateBook(
            book = book
        )
    }
}