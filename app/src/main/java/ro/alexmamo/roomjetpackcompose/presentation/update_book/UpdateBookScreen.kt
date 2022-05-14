package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksViewModel
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookContent
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookTopBar

@Composable
fun UpdateBookScreen(
    navController: NavController,
    bookId: Int,
    viewModel: BooksViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }
    Scaffold(
        topBar = {
            UpdateBookTopBar(navController)
        },
        content = { padding ->
            UpdateBookContent(
                padding = padding,
                navController = navController,
                bookId = bookId
            )
        }
    )
}