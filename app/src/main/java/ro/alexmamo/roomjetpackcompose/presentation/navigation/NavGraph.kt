package ro.alexmamo.roomjetpackcompose.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksScreen
import ro.alexmamo.roomjetpackcompose.presentation.navigation.Screen.BooksScreen
import ro.alexmamo.roomjetpackcompose.presentation.navigation.Screen.UpdateBookScreen
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
                navController = navController
            )
        }
        composable(
            route = UpdateBookScreen.route + "/{bookId}",
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            UpdateBookScreen(
                navController = navController,
                bookId = bookId
            )
        }
    }
}