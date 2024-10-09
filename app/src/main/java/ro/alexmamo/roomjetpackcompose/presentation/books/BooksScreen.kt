package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ro.alexmamo.roomjetpackcompose.components.ProgressBar
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_AUTHOR_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.EMPTY_TITLE_MESSAGE
import ro.alexmamo.roomjetpackcompose.core.printError
import ro.alexmamo.roomjetpackcompose.core.toastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksTopBar
import ro.alexmamo.roomjetpackcompose.presentation.books.components.EmptyContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.InsertBookAlertDialog
import ro.alexmamo.roomjetpackcompose.presentation.books.components.InsertBookFloatingActionButton

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToUpdateBookScreen: (id: Int) -> Unit
) {
    val context = LocalContext.current
    var openInsertBookDialog by remember { mutableStateOf(false) }
    val response by viewModel.response.collectAsStateWithLifecycle(
        initialValue = Loading
    )
    var insertingBook by remember { mutableStateOf(false) }
    var deletingBook by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BooksTopBar()
        },
        content = { padding ->
            when(val booksResponse = response) {
                is Loading -> ProgressBar()
                is Success -> {
                    val books = booksResponse.data
                    if (books.isEmpty()) {
                        EmptyContent()
                    } else {
                        BooksContent(
                            padding = padding,
                            books = books,
                            deleteBook = { book ->
                                viewModel.deleteBook(book)
                                deletingBook = true
                            },
                            navigateToUpdateBookScreen = navigateToUpdateBookScreen
                        )
                    }
                }
                is Failure -> printError(booksResponse.e)
            }
        },
        floatingActionButton = {
            InsertBookFloatingActionButton(
                openDialog = {
                    openInsertBookDialog = true
                }
            )
        }
    )

    if (openInsertBookDialog) {
        InsertBookAlertDialog(
            showEmptyTitleMessage = {
                toastMessage(context, EMPTY_TITLE_MESSAGE)
            },
            showEmptyAuthorMessage = {
                toastMessage(context, EMPTY_AUTHOR_MESSAGE)
            },
            insertBook = { book ->
                viewModel.insertBook(book)
                insertingBook = true
            },
            closeDialog = {
                openInsertBookDialog = false
            }
        )
    }

    if (insertingBook) {
        val insertBookResponse = viewModel.insertBookResponse
        when(insertBookResponse) {
            is Loading -> ProgressBar()
            is Success -> insertingBook = false
            is Failure -> printError(insertBookResponse.e)
        }
    }

    if (deletingBook) {
        val deleteBookResponse = viewModel.deleteBookResponse
        when(deleteBookResponse) {
            is Loading -> ProgressBar()
            is Success -> deletingBook = false
            is Failure -> printError(deleteBookResponse.e)
        }
    }
}