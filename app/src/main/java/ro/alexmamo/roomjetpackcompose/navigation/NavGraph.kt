package ro.alexmamo.roomjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ro.alexmamo.roomjetpackcompose.core.toBook
import ro.alexmamo.roomjetpackcompose.core.toUpdateBookScreen
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksScreen
import ro.alexmamo.roomjetpackcompose.presentation.update_book.UpdateBookScreen

@Composable
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BooksScreen
    ) {
        composable<BooksScreen> {
            BooksScreen(
                navigateToUpdateBookScreen = { book ->
                    val updateBookScreen = book.toUpdateBookScreen()
                    navController.navigate(
                        route = updateBookScreen
                    )
                }
            )
        }
        composable<UpdateBookScreen>  { entry ->
            val updateBookScreen = entry.toRoute<UpdateBookScreen>()
            val book = updateBookScreen.toBook()
            UpdateBookScreen(
                book = book,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}