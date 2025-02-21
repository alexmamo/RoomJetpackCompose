package ro.alexmamo.roomjetpackcompose.presentation.book_list

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.components.LoadingIndicator
import ro.alexmamo.roomjetpackcompose.core.logMessage
import ro.alexmamo.roomjetpackcompose.core.showSnackbarMessage
import ro.alexmamo.roomjetpackcompose.core.showToastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.BookListContent
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.BookListTopBar
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.EmptyBookListContent
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.InsertBookAlertDialog
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.InsertBookFloatingActionButton

const val ADDED_STATE = "added"
const val UPDATED_STATE = "updated"
const val DELETED_STATE = "deleted"

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    navigateToBookDetailsScreen: (Book) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var openInsertBookDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BookListTopBar()
        },
        floatingActionButton = {
            InsertBookFloatingActionButton(
                onInsertBookFloatingActionButtonClick = {
                    openInsertBookDialog = true
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        when(val bookListResponse = viewModel.bookListResponseState.collectAsStateWithLifecycle().value) {
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> bookListResponse.data.let { bookList ->
                if (bookList.isEmpty()) {
                    EmptyBookListContent(
                        innerPadding = innerPadding
                    )
                } else {
                    BookListContent(
                        innerPadding = innerPadding,
                        bookList = bookList,
                        onBookCardClick = navigateToBookDetailsScreen,
                        onUpdateBook = { book ->
                            viewModel.updateBook(book)
                        },
                        onEmptyBookField = { bookField ->
                            showSnackbarMessage(
                                coroutineScope = coroutineScope,
                                snackbarHostState = snackbarHostState,
                                message = resources.getString(R.string.empty_book_field_message, bookField)
                            )
                        },
                        onDeleteBook = { bookId ->
                            viewModel.deleteBook(bookId)
                        },
                        onNoUpdates = {
                            showSnackbarMessage(
                                coroutineScope = coroutineScope,
                                snackbarHostState = snackbarHostState,
                                message = resources.getString(R.string.no_book_updates_message)
                            )
                        }
                    )
                }
            }
            is Response.Failure -> bookListResponse.e.message?.let { errorMessage ->
                LaunchedEffect(errorMessage) {
                    logMessage(errorMessage)
                    showToastMessage(context, errorMessage)
                }
            }
        }
    }

    if (openInsertBookDialog) {
        InsertBookAlertDialog(
            onInsertBook = { book ->
                viewModel.insertBook(book)
            },
            onEmptyBookField = { emptyField ->
                showSnackbarMessage(
                    coroutineScope = coroutineScope,
                    snackbarHostState = snackbarHostState,
                    message = resources.getString(R.string.empty_book_field_message, emptyField)
                )
            },
            onInsertBookDialogCancel = {
                openInsertBookDialog = false
            }
        )
    }

    when(val insertBookResponse = viewModel.insertBookResponse.collectAsStateWithLifecycle().value) {
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showSnackbarMessage(
                coroutineScope = coroutineScope,
                snackbarHostState = snackbarHostState,
                message = resources.getString(R.string.book_state_message, ADDED_STATE)
            )
            viewModel.resetInsertBookState()
        }
        is Response.Failure -> insertBookResponse.e.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logMessage(errorMessage)
                showToastMessage(context, errorMessage)
            }
        }
        null -> {}
    }

    when(val updateBookResponse = viewModel.updateBookResponse.collectAsStateWithLifecycle().value) {
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showSnackbarMessage(
                coroutineScope = coroutineScope,
                snackbarHostState = snackbarHostState,
                message = resources.getString(R.string.book_state_message, UPDATED_STATE)
            )
            viewModel.resetUpdateBookState()
        }
        is Response.Failure -> updateBookResponse.e.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logMessage(errorMessage)
                showToastMessage(context, errorMessage)
            }
        }
        null -> {}
    }

    when(val deleteBookResponse = viewModel.deleteBookResponse.collectAsStateWithLifecycle().value) {
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showSnackbarMessage(
                coroutineScope = coroutineScope,
                snackbarHostState = snackbarHostState,
                message = resources.getString(R.string.book_state_message, DELETED_STATE)
            )
            viewModel.resetDeleteBookState()
        }
        is Response.Failure -> deleteBookResponse.e.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logMessage(errorMessage)
                showToastMessage(context, errorMessage)
            }
        }
        null -> {}
    }
}