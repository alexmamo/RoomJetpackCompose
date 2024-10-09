package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.components.ProgressBar
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_AUTHOR_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_TITLE_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.NO_UPDATES_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.printError
import ro.alexmamo.roomjetpackcompose.core.toastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookContent
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookTopBar

@Composable
fun UpdateBookScreen(
    viewModel: UpdateBookViewModel = hiltViewModel(),
    id: Int,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    var updatingBook by remember { mutableStateOf(false) }
    LaunchedEffect(id) {
        viewModel.getBook(id)
    }

    Scaffold(
        topBar = {
            UpdateBookTopBar(
                onArrowBackIconClick = navigateBack
            )
        },
        content = { padding ->
            val bookResponse = viewModel.bookResponse
            when(bookResponse) {
                is Loading -> ProgressBar()
                is Success -> {
                    val book = bookResponse.data
                    UpdateBookContent(
                        padding = padding,
                        book = book,
                        showEmptyTitleMessage = {
                            toastMessage(context, EMPTY_TITLE_MESSAGE)
                        },
                        showEmptyAuthorMessage = {
                            toastMessage(context, EMPTY_AUTHOR_MESSAGE)
                        },
                        updateBook = { book ->
                            viewModel.updateBook(book)
                            updatingBook = true
                        },
                        showNoUpdatesMessage = {
                            toastMessage(context, NO_UPDATES_MESSAGE)
                        },
                        navigateBack = navigateBack
                    )
                }
                is Failure -> printError(bookResponse.e)
            }
        }
    )

    if (updatingBook) {
        val updateBookResponse = viewModel.updateBookResponse
        when(updateBookResponse) {
            is Loading -> ProgressBar()
            is Success -> updatingBook = false
            is Failure -> printError(updateBookResponse.e)
        }
    }
}