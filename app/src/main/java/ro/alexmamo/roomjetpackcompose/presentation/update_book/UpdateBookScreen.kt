package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksViewModel
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookContent
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookTopBar

@Composable
fun UpdateBookScreen(
    bookId: Int,
    navigateToBooksScreen: () -> Unit,
    viewModel: BooksViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }
    Scaffold(
        topBar = {
            UpdateBookTopBar(
                navigateToBooksScreen = navigateToBooksScreen
            )
        },
        content = { padding ->
            UpdateBookContent(
                padding = padding,
                bookId = bookId,
                navigateToBooksScreen = navigateToBooksScreen
            )
        }
    )
}