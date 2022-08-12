package ro.alexmamo.roomjetpackcompose.data.repository

import ro.alexmamo.roomjetpackcompose.data.network.BookDao
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooksFromRoom() = bookDao.getBooks()

    override fun getBookFromRoom(id: Int) = bookDao.getBook(id)

    override fun addBookToRoom(book: Book) = bookDao.addBook(book)

    override fun updateBookInRoom(book: Book) = bookDao.updateBook(book)

    override fun deleteBookFromRoom(book: Book) = bookDao.deleteBook(book)
}