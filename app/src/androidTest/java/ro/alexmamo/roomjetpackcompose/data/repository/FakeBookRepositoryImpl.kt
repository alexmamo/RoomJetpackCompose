package ro.alexmamo.roomjetpackcompose.data.repository

import kotlinx.coroutines.flow.flow
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository

class FakeBookRepositoryImpl() : BookRepository {
    private val bookList = mutableListOf<Book>()

    override fun getBookList() = flow {
        emit(bookList)
    }

    override suspend fun getBookById(id: Int) = bookList.find { book ->
        book.id == id
    }

    override suspend fun insertBook(book: Book) {
        bookList.add(book)
    }

    override suspend fun updateBook(book: Book) {
        val indexOfFirstBook = bookList.indexOfFirst { firstBook ->
            firstBook.id == book.id
        }
        if (indexOfFirstBook != -1) {
            bookList[indexOfFirstBook] = book
        }
    }

    override suspend fun deleteBook(book: Book) {
        bookList.remove(book)
    }
}