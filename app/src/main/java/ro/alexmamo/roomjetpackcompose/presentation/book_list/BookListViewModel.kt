package ro.alexmamo.roomjetpackcompose.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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
    val bookListState = repo.getBookList().map { bookList ->
        try {
            Response.Success(bookList)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Response.Loading
    )

    private val _insertBookState = MutableStateFlow<InsertBookResponse>(Response.Idle)
    val insertBookState: StateFlow<InsertBookResponse> = _insertBookState.asStateFlow()

    private val _updateBookState = MutableStateFlow<UpdateBookResponse>(Response.Idle)
    val updateBookState: StateFlow<UpdateBookResponse> = _updateBookState.asStateFlow()

    private val _deleteBookState = MutableStateFlow<DeleteBookResponse>(Response.Idle)
    val deleteBookState: StateFlow<DeleteBookResponse> = _deleteBookState.asStateFlow()

    fun insertBook(book: Book) = viewModelScope.launch {
        try {
            _insertBookState.value = Response.Loading
            _insertBookState.value = Response.Success(repo.insertBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetInsertBookState() {
        _insertBookState.value = Response.Idle
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        try {
            _updateBookState.value = Response.Loading
            _updateBookState.value = Response.Success(repo.updateBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetUpdateBookState() {
        _updateBookState.value = Response.Idle
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        try {
            _deleteBookState.value = Response.Loading
            _deleteBookState.value = Response.Success(repo.deleteBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetDeleteBookState() {
        _deleteBookState.value = Response.Idle
    }
}