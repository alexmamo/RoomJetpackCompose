package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book

typealias Books = List<Book>

interface BookRepository {
    fun getBooks(): Flow<Books>

    suspend fun getBookById(id: Int): Book

    suspend fun insertBook(book: Book)

    suspend fun updateBook(book: Book)

    suspend fun deleteBook(book: Book)
}