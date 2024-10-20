package ro.alexmamo.roomjetpackcompose.presentation.update_book

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
import ro.alexmamo.roomjetpackcompose.domain.repository.UpdateBookResponse
import javax.inject.Inject

@HiltViewModel
class UpdateBookViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    var updateBookResponse by mutableStateOf<UpdateBookResponse>(Loading)
        private set

    fun updateBook(book: Book) = viewModelScope.launch {
        updateBookResponse = repo.updateBook(book)
    }
}