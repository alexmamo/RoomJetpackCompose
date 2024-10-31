package ro.alexmamo.roomjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ro.alexmamo.roomjetpackcompose.domain.model.toUpdateBook
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksScreen
import ro.alexmamo.roomjetpackcompose.presentation.update_book.UpdateBookScreen

@Composable
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Books
    ) {
        composable<Books> {
            BooksScreen(
                navigateToUpdateBookScreen = { book ->
                    val updateBook = book.toUpdateBook()
                    navController.navigate(updateBook)
                }
            )
        }
        composable<UpdateBook>  { entry ->
            val updateBook = entry.toRoute<UpdateBook>()
            val book = updateBook.toBook()
            UpdateBookScreen(
                book = book,
                navigateBack = navController::navigateUp
            )
        }
    }
}