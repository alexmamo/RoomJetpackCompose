package ro.alexmamo.roomjetpackcompose.repository

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

    override suspend fun updateBook(updatedBook: Book) {
        val index = bookList.indexOfFirst { book ->
            book.id == updatedBook.id
        }
        if (index != -1) {
            bookList[index] = updatedBook
        }
    }

    override suspend fun deleteBook(book: Book) {
        bookList.remove(book)
    }
}