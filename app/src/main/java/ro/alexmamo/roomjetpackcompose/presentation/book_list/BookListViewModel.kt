package ro.alexmamo.roomjetpackcompose.presentation.book_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ro.alexmamo.roomjetpackcompose.core.launchCatching
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import javax.inject.Inject

typealias InsertBookResponse = Response<Unit>
typealias UpdateBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    val bookListResponseState = repo.getBookList().map { bookList ->
        launchCatching {
            bookList
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Response.Loading
    )

    var insertBookResponse by mutableStateOf<InsertBookResponse>(Response.Loading)
        private set
    var updateBookResponse by mutableStateOf<UpdateBookResponse>(Response.Loading)
        private set
    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Response.Loading)
        private set

    fun insertBook(book: Book) = viewModelScope.launch {
        insertBookResponse = launchCatching {
            repo.insertBook(book)
        }
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        updateBookResponse = launchCatching {
            repo.updateBook(book)
        }
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        deleteBookResponse = launchCatching {
            repo.deleteBook(book)
        }
    }
}