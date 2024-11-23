package ro.alexmamo.roomjetpackcompose

import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.navigation.NavGraph
import ro.alexmamo.roomjetpackcompose.presentation.MainActivity

@HiltAndroidTest
class BookNavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(
                navController = navController
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun testStartDestinationByRoute() {
        val startDestination = navController.graph.startDestinationRoute
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(currentDestination).isEqualTo(startDestination)
    }

    @Test
    fun testStartDestinationByText() {
        composeTestRule
            .onNodeWithText(getString(R.string.book_list_screen_title))
            .assertIsDisplayed()
    }

    @Test
    fun testBookInsertAndNavigationToBookDetailsScreenAndBackToBookListScreen() {
        composeTestRule.apply {
            onNodeWithContentDescription(getString(R.string.open_insert_book_dialog))
                .performClick()
            onNodeWithText(getString(R.string.book_title))
                .performTextInput(getString(R.string.title_test))
            onNodeWithText(getString(R.string.book_author))
                .performTextInput(getString(R.string.author_test))
            onNodeWithText(getString(R.string.insert_button))
                .performClick()
            onNodeWithText(getString(R.string.title_test))
                .performClick()
            onNodeWithText(getString(R.string.book_details_screen_title))
                .assertIsDisplayed()
            onNodeWithText(getString(R.string.title_test))
                .assertIsDisplayed()
            onNodeWithText("by ${getString(R.string.author_test)}")
                .assertIsDisplayed()
            onNodeWithContentDescription(getString(R.string.navigate_back))
                .performClick()
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
        }
    }

    private fun getString(@StringRes resId: Int) = composeTestRule.activity.getString(resId)
}