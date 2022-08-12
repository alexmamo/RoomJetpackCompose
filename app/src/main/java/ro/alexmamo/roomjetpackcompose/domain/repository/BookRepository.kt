package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book

interface BookRepository {
    fun getBooksFromRoom(): Flow<List<Book>>

    fun getBookFromRoom(id: Int): Flow<Book>

    fun addBookToRoom(book: Book)

    fun updateBookInRoom(book: Book)

    fun deleteBookFromRoom(book: Book)
}