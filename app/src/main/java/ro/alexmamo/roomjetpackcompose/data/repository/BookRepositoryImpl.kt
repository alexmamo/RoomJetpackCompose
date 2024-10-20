package ro.alexmamo.roomjetpackcompose.data.repository

import kotlinx.coroutines.flow.flow
import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooks() = flow {
        try {
            bookDao.getBooks().collect { books ->
                emit(Success(books))
            }
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override suspend fun insertBook(book: Book) = try {
        bookDao.insertBook(book)
        Success(Unit)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun updateBook(book: Book) = try {
        bookDao.updateBook(book)
        Success(Unit)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteBook(book: Book) = try {
        bookDao.deleteBook(book)
        Success(Unit)
    } catch (e: Exception) {
        Failure(e)
    }
}