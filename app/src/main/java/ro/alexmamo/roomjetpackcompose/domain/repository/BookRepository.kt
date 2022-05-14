package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book

interface BookRepository {
    suspend fun getBooksFromRoom(): Flow<List<Book>>

    suspend fun getBookFromRoom(id: Int): Flow<Book>

    suspend fun addBookToRoom(book: Book)

    suspend fun updateBookInRoom(book: Book)

    suspend fun deleteBookFromRoom(book: Book)
}