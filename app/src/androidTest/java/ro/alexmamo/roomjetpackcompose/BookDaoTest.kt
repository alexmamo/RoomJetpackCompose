package ro.alexmamo.roomjetpackcompose

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.data.dao.BookDao
import ro.alexmamo.roomjetpackcompose.data.network.BookDb
import ro.alexmamo.roomjetpackcompose.utils.getBookTest
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
class BookDaoTest() {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bookDao: BookDao
    @Inject
    lateinit var bookDb: BookDb

    val context = ApplicationProvider.getApplicationContext<Context>()
    private val bookTest = getBookTest(context)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndGetBookById() = runTest {
        bookDao.insertBook(bookTest)
        val book = bookDao.getBookById(bookTest.id)
        assertThat(book).isEqualTo(bookTest)
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndCheckIfBookExistsInBookList() = runTest {
        bookDao.insertBook(bookTest)
        val bookList = bookDao.getBookList().first()
        assertThat(bookTest).isIn(bookList)
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndCheckTheSizeOfBookList() = runTest {
        bookDao.insertBook(bookTest)
        val bookList = bookDao.getBookList().first()
        assertThat(bookList.size).isEqualTo(1)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdateAndGetBookById() = runTest {
        bookDao.insertBook(bookTest)
        bookDao.updateBook(
            book = bookTest.copy(
                title = context.getString(R.string.new_title_test)
            )
        )
        val book = bookDao.getBookById(bookTest.id)
        assertThat(book.title).isEqualTo(context.getString(R.string.new_title_test))
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndDeleteAndCheckTheSizeOfBookList() = runTest {
        bookDao.insertBook(bookTest)
        bookDao.deleteBook(bookTest)
        val bookList = bookDao.getBookList().first()
        assertThat(bookList).isEmpty()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        bookDb.close()
    }
}