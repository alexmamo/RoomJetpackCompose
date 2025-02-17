package ro.alexmamo.roomjetpackcompose.presentation.book_list

import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.components.LoadingIndicator
import ro.alexmamo.roomjetpackcompose.core.printError
import ro.alexmamo.roomjetpackcompose.core.showMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.BookError
import ro.alexmamo.roomjetpackcompose.domain.model.Response
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.BookListContent
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.BookListTopBar
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.EmptyBookListContent
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.InsertBookAlertDialog
import ro.alexmamo.roomjetpackcompose.presentation.book_list.components.InsertBookFloatingActionButton

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    navigateToBookDetailsScreen: (Book) -> Unit
) {
    val context = LocalContext.current
    val bookListResponse by viewModel.bookListResponseState.collectAsStateWithLifecycle()
    var openInsertBookDialog by remember { mutableStateOf(false) }
    var insertingBook by remember { mutableStateOf(false) }
    var updatingBook by remember { mutableStateOf(false) }
    var deletingBook by remember { mutableStateOf(false) }

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
        }
    ) { innerPadding ->
        when(val bookListResponse = bookListResponse) {
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> bookListResponse.data.let { bookList ->
                if (bookList.isEmpty()) {
                    EmptyBookListContent()
                } else {
                    BookListContent(
                        innerPadding = innerPadding,
                        bookList = bookList,
                        onBookCardClick = navigateToBookDetailsScreen,
                        onUpdateBook = { book ->
                            viewModel.updateBook(book)
                            updatingBook = true
                        },
                        onUpdateBookError = { bookError ->
                            showBookErrorMessage(context, bookError)
                        },
                        onDeleteBook = { book ->
                            viewModel.deleteBook(book)
                            deletingBook = true
                        },
                        onNoUpdates = {
                            showNoBookUpdatesMessage(context)
                        }
                    )
                }
            }
            is Response.Failure -> printError(bookListResponse.e)
        }
    }

    if (openInsertBookDialog) {
        InsertBookAlertDialog(
            onInsertBook = { book ->
                viewModel.insertBook(book)
                insertingBook = true
            },
            onInsertBookError = { bookError ->
                showBookErrorMessage(context, bookError)
            },
            onInsertBookDialogCancel = {
                openInsertBookDialog = false
            }
        )
    }

    if (insertingBook) {
        when(val insertBookResponse = viewModel.insertBookResponse) {
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> insertingBook = false
            is Response.Failure -> printError(insertBookResponse.e)
        }
    }

    if (updatingBook) {
        when(val updateBookResponse = viewModel.updateBookResponse) {
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> updatingBook = false
            is Response.Failure -> printError(updateBookResponse.e)
        }
    }

    if (deletingBook) {
        when(val deleteBookResponse = viewModel.deleteBookResponse) {
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> deletingBook = false
            is Response.Failure -> printError(deleteBookResponse.e)
        }
    }
}

fun showBookErrorMessage(
    context: Context,
    bookError: BookError
) {
    val resourceId = when(bookError) {
        BookError.EmptyTitle -> R.string.empty_book_title_message
        BookError.EmptyAuthor -> R.string.empty_book_author_message
    }
    showMessage(context, resourceId)
}

fun showNoBookUpdatesMessage(
    context: Context
) = showMessage(
    context = context,
    resourceId = R.string.no_book_updates_message
)