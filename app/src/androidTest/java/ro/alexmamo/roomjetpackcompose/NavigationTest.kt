package ro.alexmamo.roomjetpackcompose

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.navigation.NavGraph
import ro.alexmamo.roomjetpackcompose.presentation.MainActivity

@HiltAndroidTest
class NavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var navController: TestNavHostController

    val context = ApplicationProvider.getApplicationContext<Context>()

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
            .onNodeWithText(context.getString(R.string.book_list_screen_title))
            .assertIsDisplayed()
    }

    @Test
    fun onTest() {
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.open_insert_book_dialog))
            .performClick()
        composeTestRule
            .onNodeWithText(context.getString(R.string.book_title))
            .performTextInput(context.getString(R.string.title_test))
        composeTestRule
            .onNodeWithText(context.getString(R.string.book_author))
            .performTextInput(context.getString(R.string.author_test))
        composeTestRule
            .onNodeWithText(context.getString(R.string.insert_button))
            .performClick()
        composeTestRule
            .onNodeWithText(context.getString(R.string.title_test))
            .performClick()
        composeTestRule
            .onNodeWithText(context.getString(R.string.book_details_screen_title))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(context.getString(R.string.title_test))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("by ${context.getString(R.string.author_test)}")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.navigate_back))
            .performClick()
        composeTestRule
            .onNodeWithText(context.getString(R.string.book_list_screen_title))
            .assertIsDisplayed()
    }
}