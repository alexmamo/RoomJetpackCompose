package ro.alexmamo.roomjetpackcompose.presentation.book_list

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
typealias UpdateBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    var insertBookResponse by mutableStateOf<InsertBookResponse>(Loading)
        private set
    var updateBookResponse by mutableStateOf<UpdateBookResponse>(Loading)
        private set
    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Loading)
        private set

    val bookListResponseFlow = flow {
        repo.getBookList().collect { bookList ->
            val bookListResponse = launchCatching {
                bookList
            }
            emit(bookListResponse)
        }
    }

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