package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.components.LoadingIndicator
import ro.alexmamo.roomjetpackcompose.core.EMPTY_AUTHOR_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.EMPTY_TITLE_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.NO_UPDATES_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.printError
import ro.alexmamo.roomjetpackcompose.core.showToastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookContent
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookTopBar

@Composable
fun UpdateBookScreen(
    viewModel: UpdateBookViewModel = hiltViewModel(),
    book: Book,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    var updatingBook by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            UpdateBookTopBar(
                onArrowBackIconClick = navigateBack
            )
        },
        content = { padding ->
            UpdateBookContent(
                padding = padding,
                book = book,
                showEmptyTitleMessage = {
                    showToastMessage(context, EMPTY_TITLE_MESSAGE)
                },
                showEmptyAuthorMessage = {
                    showToastMessage(context, EMPTY_AUTHOR_MESSAGE)
                },
                updateBook = { book ->
                    viewModel.updateBook(book)
                    updatingBook = true
                },
                showNoUpdatesMessage = {
                    showToastMessage(context, NO_UPDATES_MESSAGE)
                },
                navigateBack = navigateBack
            )
        }
    )

    if (updatingBook) {
        val updateBookResponse = viewModel.updateBookResponse
        when(updateBookResponse) {
            is Loading -> LoadingIndicator()
            is Success -> updatingBook = false
            is Failure -> printError(updateBookResponse.e)
        }
    }
}