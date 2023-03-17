package ro.alexmamo.roomjetpackcompose.data.repository

import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooksFromRoom() = bookDao.getBooks()

    override suspend fun getBookFromRoom(id: Int) = bookDao.getBook(id)

    override suspend fun addBookToRoom(book: Book) = bookDao.addBook(book)

    override suspend fun updateBookInRoom(book: Book) = bookDao.updateBook(book)

    override suspend fun deleteBookFromRoom(book: Book) = bookDao.deleteBook(book)
}