package ro.alexmamo.roomjetpackcompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_ID
import ro.alexmamo.roomjetpackcompose.navigation.Screen.BooksScreen
import ro.alexmamo.roomjetpackcompose.navigation.Screen.UpdateBookScreen
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksScreen
import ro.alexmamo.roomjetpackcompose.presentation.update_book.UpdateBookScreen

@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BooksScreen.route
    ) {
        composable(
            route = BooksScreen.route
        ) {
            BooksScreen(
                navigateToUpdateBookScreen = { bookId ->
                    navController.navigate(
                        route = "${UpdateBookScreen.route}/${bookId}"
                    )
                }
            )
        }
        composable(
            route = "${UpdateBookScreen.route}/{$BOOK_ID}",
            arguments = listOf(
                navArgument(BOOK_ID) {
                    type = IntType
                }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt(BOOK_ID) ?: 0
            UpdateBookScreen(
                bookId = bookId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}