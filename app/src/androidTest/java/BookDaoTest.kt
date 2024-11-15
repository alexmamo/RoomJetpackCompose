import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.data.network.BookDb
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@RunWith(AndroidJUnit4::class)
class BookDaoTest {
    private lateinit var bookDao: BookDao
    private lateinit var bookDb: BookDb
    private lateinit var cleanArchitecture: Book
    private lateinit var updatedCleanArchitecture: Book
    private lateinit var thinkingInJava: Book

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bookDb = Room.inMemoryDatabaseBuilder(
            context,
            BookDb::class.java
        ).build()
        bookDao = bookDb.bookDao
    }

    @Before
    fun createBooks() {
        cleanArchitecture = Book(
            id = 1,
            title = "Clean Architecture",
            author = "Robert C. Martin"
        )
        updatedCleanArchitecture = cleanArchitecture.copy(
            title = "Clean Code"
        )
        thinkingInJava = Book(
            id = 2,
            title = "Thinking In Java",
            author = "Bruce Eckel"
        )
    }

    @Test
    fun bookDao_insert_and_retrieve_books() = runTest {
        bookDao.insertBook(cleanArchitecture)
        bookDao.insertBook(thinkingInJava)

        val bookList = bookDao.getBookList().first()

        Assert.assertTrue(bookList.contains(cleanArchitecture))
        Assert.assertTrue(bookList.contains(thinkingInJava))
        Assert.assertEquals(2, bookList.size)
    }

    @Test
    fun bookDao_insert_update_and_retrieve_books() = runTest {
        bookDao.insertBook(cleanArchitecture)
        bookDao.insertBook(thinkingInJava)
        bookDao.updateBook(
            book = updatedCleanArchitecture
        )

        val bookList = bookDao.getBookList().first()

        Assert.assertTrue(bookList.contains(updatedCleanArchitecture))
        Assert.assertTrue(bookList.contains(thinkingInJava))
        Assert.assertEquals(2, bookList.size)
    }

    @Test
    fun bookDao_insert_delete_and_retrieve_books() = runTest {
        bookDao.insertBook(cleanArchitecture)
        bookDao.insertBook(thinkingInJava)
        bookDao.deleteBook(cleanArchitecture)

        val bookList = bookDao.getBookList().first()

        Assert.assertFalse(bookList.contains(cleanArchitecture))
        Assert.assertTrue(bookList.contains(thinkingInJava))
        Assert.assertEquals(1, bookList.size)
    }

    @After
    fun closeDb() {
        bookDb.close()
    }
}