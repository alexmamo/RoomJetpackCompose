package ro.alexmamo.roomjetpackcompose.data.repository

import kotlinx.coroutines.flow.flow
import ro.alexmamo.roomjetpackcompose.core.launchCatching
import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooks() = flow {
        bookDao.getBooks().collect { books ->
            emit(launchCatching {
                books
            })
        }
    }

    override suspend fun insertBook(book: Book) = launchCatching {
        bookDao.insertBook(book)
    }

    override suspend fun updateBook(book: Book) = launchCatching {
        bookDao.updateBook(book)
    }

    override suspend fun deleteBook(book: Book) = launchCatching {
        bookDao.deleteBook(book)
    }
}