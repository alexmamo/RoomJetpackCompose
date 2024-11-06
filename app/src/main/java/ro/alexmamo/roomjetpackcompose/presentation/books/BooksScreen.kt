package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.components.LoadingIndicator
import ro.alexmamo.roomjetpackcompose.core.printError
import ro.alexmamo.roomjetpackcompose.core.showToastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksTopBar
import ro.alexmamo.roomjetpackcompose.presentation.books.components.EmptyContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.InsertBookAlertDialog

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToUpdateBookScreen: (Book) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    val booksResponse by viewModel.booksResponseFlow.collectAsStateWithLifecycle(Loading)
    var openInsertBookDialog by remember { mutableStateOf(false) }
    var insertingBook by remember { mutableStateOf(false) }
    var deletingBook by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BooksTopBar()
        },
        content = { padding ->
            when(val booksResponse = booksResponse) {
                is Loading -> LoadingIndicator()
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
                            navigateToUpdateBookScreen = { book ->
                                navigateToUpdateBookScreen(book)
                            }
                        )
                    }
                }
                is Failure -> printError(booksResponse.e)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
                    openInsertBookDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(
                        id = R.string.open_insert_book_dialog
                    )
                )
            }
        }
    )

    if (openInsertBookDialog) {
        InsertBookAlertDialog(
            showEmptyTitleMessage = {
                showToastMessage(
                    context = context,
                    message = resources.getString(R.string.empty_title_message)
                )
            },
            showEmptyAuthorMessage = {
                showToastMessage(
                    context = context,
                    message = resources.getString(R.string.empty_author_message)
                )
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
            is Loading -> LoadingIndicator()
            is Success -> insertingBook = false
            is Failure -> printError(insertBookResponse.e)
        }
    }

    if (deletingBook) {
        val deleteBookResponse = viewModel.deleteBookResponse
        when(deleteBookResponse) {
            is Loading -> LoadingIndicator()
            is Success -> deletingBook = false
            is Failure -> printError(deleteBookResponse.e)
        }
    }
}