package ro.alexmamo.roomjetpackcompose.data.repository

import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBookList() = bookDao.getBookList()

    override suspend fun getBookById(id: Int) = bookDao.getBookById(id)

    override suspend fun insertBook(book: Book) = bookDao.insertBook(book)

    override suspend fun updateBook(book: Book) = bookDao.updateBook(book)

    override suspend fun deleteBook(book: Book) = bookDao.deleteBook(book)
}