package ro.alexmamo.roomjetpackcompose.domain

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
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
import ro.alexmamo.roomjetpackcompose.utils.getUpdatedBookTest
import javax.inject.Inject

@HiltAndroidTest
class BookRepositoryTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Inject
    lateinit var fakeRepo: BookRepository

    val context = ApplicationProvider.getApplicationContext<Context>()
    private val bookTest = getBookTest(context)
    private val updatedBookTest = getUpdatedBookTest(context)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testInsertAndGetBookById() = runBlocking {
        fakeRepo.insertBook(bookTest)
        val book = fakeRepo.getBookById(bookTest.id)
        Truth.assertThat(book).isEqualTo(bookTest)
    }

    @Test
    fun testInsertAndCheckIfBookExistsInBookList() = runBlocking {
        fakeRepo.insertBook(bookTest)
        val bookList = fakeRepo.getBookList().first()
        Truth.assertThat(bookTest).isIn(bookList)
    }

    @Test
    fun testInsertAndCheckTheSizeOfBookList() = runTest {
        fakeRepo.insertBook(bookTest)
        val bookList = fakeRepo.getBookList().first()
        Truth.assertThat(bookList.size).isEqualTo(1)
    }

    @Test
    fun testUpdateAndGetBookById() = runTest {
        fakeRepo.insertBook(bookTest)
        fakeRepo.updateBook(updatedBookTest)
        val book = fakeRepo.getBookById(bookTest.id)
        Truth.assertThat(book?.title).isEqualTo(updatedBookTest.title)
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndDeleteAndCheckTheSizeOfBookList() = runTest {
        fakeRepo.insertBook(bookTest)
        fakeRepo.deleteBook(bookTest)
        val bookList = fakeRepo.getBookList().first()
        Truth.assertThat(bookList).isEmpty()
    }
}