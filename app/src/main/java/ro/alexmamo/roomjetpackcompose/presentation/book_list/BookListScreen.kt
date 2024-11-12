package ro.alexmamo.roomjetpackcompose.presentation.book_list

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
import ro.alexmamo.roomjetpackcompose.core.showToastMessage
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Failure
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.roomjetpackcompose.domain.model.Response.Success
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
    val bookListResponse by viewModel.bookListResponseFlow.collectAsStateWithLifecycle(Loading)
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
            is Loading -> LoadingIndicator()
            is Success -> bookListResponse.data.let { bookList ->
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
                        onEmptyTitleUpdate = {
                            showToastMessage(context, R.string.empty_title_message)
                        },
                        onEmptyAuthorUpdate = {
                            showToastMessage(context, R.string.empty_author_message)
                        },
                        onNoUpdates = {
                            showToastMessage(context, R.string.no_updates_message)
                        },
                        onDeleteBook = { book ->
                            viewModel.deleteBook(book)
                            deletingBook = true
                        }
                    )
                }
            }
            is Failure -> printError(bookListResponse.e)
        }
    }

    if (openInsertBookDialog) {
        InsertBookAlertDialog(
            onEmptyTitleInsert = {
                showToastMessage(context, R.string.empty_title_message)
            },
            onEmptyAuthorInsert = {
                showToastMessage(context, R.string.empty_author_message)
            },
            onInsertBook = { book ->
                viewModel.insertBook(book)
                insertingBook = true
            },
            onInsertBookDialogCancel = {
                openInsertBookDialog = false
            }
        )
    }

    if (insertingBook) {
        when(val insertBookResponse = viewModel.insertBookResponse) {
            is Loading -> LoadingIndicator()
            is Success -> insertingBook = false
            is Failure -> printError(insertBookResponse.e)
        }
    }

    if (updatingBook) {
        when(val updateBookResponse = viewModel.updateBookResponse) {
            is Loading -> LoadingIndicator()
            is Success -> updatingBook = false
            is Failure -> printError(updateBookResponse.e)
        }
    }

    if (deletingBook) {
        when(val deleteBookResponse = viewModel.deleteBookResponse) {
            is Loading -> LoadingIndicator()
            is Success -> deletingBook = false
            is Failure -> printError(deleteBookResponse.e)
        }
    }
}