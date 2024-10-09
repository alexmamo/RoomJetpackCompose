package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import ro.alexmamo.roomjetpackcompose.domain.repository.DeleteBookResponse
import ro.alexmamo.roomjetpackcompose.domain.repository.InsertBookResponse
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    val response = repo.getBooks()

    var insertBookResponse by mutableStateOf<InsertBookResponse>(Loading)
        private set
    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Loading)
        private set

    fun insertBook(book: Book) = viewModelScope.launch {
        insertBookResponse = repo.insertBook(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        deleteBookResponse = repo.deleteBook(book)
    }
}