package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ro.alexmamo.roomjetpackcompose.core.launchCatching
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import javax.inject.Inject

typealias InsertBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    val booksResponseFlow = flow {
        repo.getBooks().collect { books ->
            val booksResponse = launchCatching {
                books
            }
            emit(booksResponse)
        }
    }

    var insertBookResponse by mutableStateOf<InsertBookResponse>(Loading)
        private set
    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Loading)
        private set

    fun insertBook(book: Book) = viewModelScope.launch {
        insertBookResponse = launchCatching {
            repo.insertBook(book)
        }
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        deleteBookResponse = launchCatching {
            repo.deleteBook(book)
        }
    }
}