package ro.alexmamo.roomjetpackcompose.presentation.books.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.presentation.books.BooksViewModel

@Composable
@ExperimentalMaterialApi
fun BooksContent(
    padding: PaddingValues,
    navigateToUpdateBookScreen: (bookId: Int) -> Unit,
    viewModel: BooksViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        LazyColumn {
            items(
                items = viewModel.books
            ) { book ->
                BookCard(
                    book = book,
                    deleteBook = {
                        viewModel.deleteBook(book)
                    },
                    navigateToUpdateBookScreen = navigateToUpdateBookScreen
                )
            }
        }
    }
}