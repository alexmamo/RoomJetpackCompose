package ro.alexmamo.roomjetpackcompose.navigation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.toRoute
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.domain.model.toBookDetails
import ro.alexmamo.roomjetpackcompose.presentation.MainActivity
import ro.alexmamo.roomjetpackcompose.presentation.book_list.BookListScreen
import ro.alexmamo.roomjetpackcompose.presentation.book_list.BookListViewModel
import ro.alexmamo.roomjetpackcompose.presentation.book_details.BookDetailsScreen
import ro.alexmamo.roomjetpackcompose.utils.getBookTest
import javax.inject.Inject

@HiltAndroidTest
class BookNavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var fakeViewModel: BookListViewModel

    val context = ApplicationProvider.getApplicationContext<Context>()
    private val bookTest = getBookTest(context)

    lateinit var navController: TestNavHostController

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Before
    fun setupNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            fakeViewModel.insertBook(bookTest)

            NavHost(
                navController = navController,
                startDestination = BookListScreen
            ) {
                composable<BookListScreen> {
                    BookListScreen(
                        viewModel = fakeViewModel,
                        navigateToBookDetailsScreen = { book ->
                            val bookDetails = book.toBookDetails()
                            navController.navigate(bookDetails)
                        }
                    )
                }
                composable<BookDetails> { entry ->
                    val bookDetails = entry.toRoute<BookDetails>()
                    val book = bookDetails.toBook()
                    BookDetailsScreen(
                        book = book,
                        navigateBack = navController::navigateUp
                    )
                }
            }
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
    fun testNavigationFromBookListScreenToBookDetailsScreen() {
        composeTestRule.apply {
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
            onNodeWithText(bookTest.title)
                .performClick()
            onNodeWithText(getString(R.string.book_details_screen_title))
                .assertIsDisplayed()
        }
    }

    @Test
    fun testNavigationFromBookListScreenToBookDetailsScreenAndBack() {
        composeTestRule.apply {
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
            onNodeWithText(bookTest.title)
                .performClick()
            onNodeWithText(getString(R.string.book_details_screen_title))
                .assertIsDisplayed()
            onNodeWithContentDescription(getString(R.string.navigate_back))
                .performClick()
            onNodeWithText(getString(R.string.book_list_screen_title))
                .assertIsDisplayed()
        }
    }

    private fun getString(@StringRes resId: Int) = composeTestRule.activity.getString(resId)
}