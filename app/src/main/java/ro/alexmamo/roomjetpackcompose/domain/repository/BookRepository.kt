package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response

typealias Books = List<Book>
typealias BooksResponse = Response<Books>
typealias InsertBookResponse = Response<Unit>
typealias UpdateBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

interface BookRepository {
    fun getBooks(): Flow<BooksResponse>

    suspend fun insertBook(book: Book): InsertBookResponse

    suspend fun updateBook(book: Book): UpdateBookResponse

    suspend fun deleteBook(book: Book): DeleteBookResponse
}