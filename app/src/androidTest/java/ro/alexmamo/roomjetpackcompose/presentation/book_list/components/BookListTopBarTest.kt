package ro.alexmamo.roomjetpackcompose.presentation.book_list.components

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.presentation.MainActivity

@HiltAndroidTest
class BookListTopBarTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testBookListTopBar() {
        composeTestRule.apply {
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
        }
    }

    private fun getString(@StringRes resId: Int) = composeTestRule.activity.getString(resId)
}