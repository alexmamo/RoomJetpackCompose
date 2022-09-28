package ro.alexmamo.roomjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book

typealias Books = List<Book>

interface BookRepository {
    fun getBooksFromRoom(): Flow<Books>

    fun getBookFromRoom(id: Int): Book

    fun addBookToRoom(book: Book)

    fun updateBookInRoom(book: Book)

    fun deleteBookFromRoom(book: Book)
}