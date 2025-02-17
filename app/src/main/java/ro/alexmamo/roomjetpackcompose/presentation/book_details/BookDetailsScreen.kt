package ro.alexmamo.roomjetpackcompose.presentation.book_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.presentation.book_details.components.BookDetailsContent
import ro.alexmamo.roomjetpackcompose.presentation.book_details.components.BookDetailsTopBar

@Composable
fun BookDetailsScreen(
    book: Book,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            BookDetailsTopBar(
                onArrowBackIconClick = navigateBack
            )
        },
        content = { innerPadding ->
            BookDetailsContent(
                innerPadding = innerPadding,
                book = book
            )
        }
    )
}