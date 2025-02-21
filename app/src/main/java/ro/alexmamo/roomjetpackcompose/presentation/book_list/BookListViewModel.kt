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
    val bookListResponseState = repo.getBookList().map { bookList ->
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

    private val _insertBookResponse = MutableStateFlow<InsertBookResponse?>(null)
    val insertBookResponse: StateFlow<InsertBookResponse?> = _insertBookResponse.asStateFlow()

    private val _updateBookResponse = MutableStateFlow<UpdateBookResponse?>(null)
    val updateBookResponse: StateFlow<UpdateBookResponse?> = _updateBookResponse.asStateFlow()

    private val _deleteBookResponse = MutableStateFlow<DeleteBookResponse?>(null)
    val deleteBookResponse: StateFlow<DeleteBookResponse?> = _deleteBookResponse.asStateFlow()

    fun insertBook(book: Book) = viewModelScope.launch {
        try {
            _insertBookResponse.value = Response.Loading
            _insertBookResponse.value = Response.Success(repo.insertBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetInsertBookState() = _insertBookResponse.value?.let {
        _insertBookResponse.value = null
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        try {
            _updateBookResponse.value = Response.Loading
            _updateBookResponse.value = Response.Success(repo.updateBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetUpdateBookState() = _updateBookResponse.value?.let {
        _updateBookResponse.value = null
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        try {
            _deleteBookResponse.value = Response.Loading
            _deleteBookResponse.value = Response.Success(repo.deleteBook(book))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetDeleteBookState() = _deleteBookResponse.value?.let {
        _deleteBookResponse.value = null
    }
}