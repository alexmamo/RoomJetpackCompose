package ro.alexmamo.roomjetpackcompose

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import ro.alexmamo.roomjetpackcompose.utils.getBookTest
import javax.inject.Inject

@HiltAndroidTest
class BookRepositoryTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Inject
    lateinit var repo: BookRepository

    val context = ApplicationProvider.getApplicationContext<Context>()
    private val bookTest = getBookTest(context)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testInsertAndGetBookById() = runBlocking {
        repo.insertBook(bookTest)
        val book = repo.getBookById(bookTest.id)
        assertThat(book).isEqualTo(bookTest)
    }

    @Test
    fun testInsertAndCheckIfBookExistsInBookList() = runBlocking {
        repo.insertBook(bookTest)
        val bookList = repo.getBookList().first()
        assertThat(bookTest).isIn(bookList)
    }

    @Test
    fun testInsertAndCheckTheSizeOfBookList() = runTest {
        repo.insertBook(bookTest)
        val bookList = repo.getBookList().first()
        assertThat(bookList.size).isEqualTo(1)
    }

    @Test
    fun testUpdateAndGetBookById() = runTest {
        repo.insertBook(bookTest)
        repo.updateBook(
            book = bookTest.copy(
                title = context.getString(R.string.new_title_test)
            )
        )
        val book = repo.getBookById(bookTest.id)
        assertThat(book?.title).isEqualTo(context.getString(R.string.new_title_test))
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndDeleteAndCheckTheSizeOfBookList() = runTest {
        repo.insertBook(bookTest)
        repo.deleteBook(bookTest)
        val bookList = repo.getBookList().first()
        assertThat(bookList).isEmpty()
    }
}