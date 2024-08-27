package ro.alexmamo.roomjetpackcompose.presentation.update_book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_AUTHOR_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_TITLE_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.toastMessage
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookContent
import ro.alexmamo.roomjetpackcompose.presentation.update_book.components.UpdateBookTopBar

@Composable
fun UpdateBookScreen(
    viewModel: UpdateBookViewModel = hiltViewModel(),
    id: Int,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(id) {
        viewModel.getBookById(id)
    }

    Scaffold(
        topBar = {
            UpdateBookTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UpdateBookContent(
                padding = padding,
                book = viewModel.book,
                showEmptyTitleMessage = {
                    toastMessage(context, EMPTY_TITLE_MESSAGE)
                },
                updateBookTitle = { title ->
                    viewModel.updateBookTitle(title)
                },
                showEmptyAuthorMessage = {
                    toastMessage(context, EMPTY_AUTHOR_MESSAGE)
                },
                updateBookAuthor = { author ->
                    viewModel.updateBookAuthor(author)
                },
                updateBook = {
                    viewModel.updateBook()
                },
                navigateBack = navigateBack
            )
        }
    )
}