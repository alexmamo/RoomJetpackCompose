package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response

typealias Books = List<Book>

interface BookRepository {
    fun getBooks(): Flow<Response<Books>>

    suspend fun getBook(id: Int): Response<Book>

    suspend fun insertBook(book: Book): Response<Unit>

    suspend fun updateBook(book: Book): Response<Unit>

    suspend fun deleteBook(book: Book): Response<Unit>
}