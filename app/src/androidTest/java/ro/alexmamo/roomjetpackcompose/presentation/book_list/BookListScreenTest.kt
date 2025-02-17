package ro.alexmamo.roomjetpackcompose.presentation.book_list

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.presentation.MainActivity
import ro.alexmamo.roomjetpackcompose.utils.getBookTest

@HiltAndroidTest
class BookListScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val context = ApplicationProvider.getApplicationContext<Context>()
    private val bookTest = getBookTest(context)

    @Test
    fun testBookClickAndNavigationToBookDetailsScreenAndBackToBookListScreen() {
        composeTestRule.apply {
            onNodeWithContentDescription(getString(R.string.open_insert_book_dialog))
                .performClick()
            onNodeWithText(getString(R.string.book_title))
                .performTextInput(bookTest.title)
            onNodeWithText(getString(R.string.book_author))
                .performTextInput(bookTest.author)
            onNodeWithText(getString(R.string.insert_button))
                .performClick()
            onNodeWithText(bookTest.title)
                .performClick()
            onNodeWithText(getString(R.string.book_details_screen_title))
                .assertIsDisplayed()
            onNodeWithText(bookTest.title)
                .assertIsDisplayed()
            onNodeWithText("by ${bookTest.author}")
                .assertIsDisplayed()
            onNodeWithContentDescription(getString(R.string.navigate_back))
                .performClick()
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
        }
    }

    private fun getString(@StringRes resId: Int) = composeTestRule.activity.getString(resId)
}