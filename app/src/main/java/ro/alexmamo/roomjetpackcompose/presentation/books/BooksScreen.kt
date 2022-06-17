package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.roomjetpackcompose.presentation.books.components.AddBookAlertDialog
import ro.alexmamo.roomjetpackcompose.presentation.books.components.AddBookFloatingActionButton
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksTopBar

@Composable
@ExperimentalMaterialApi
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToUpdateBookScreen: (bookId: Int) -> Unit
) {
    val isDialogOpen = viewModel.isDialogOpen
    fun openDialog() {
        viewModel.isDialogOpen = true
    }
    fun closeDialog() {
        viewModel.isDialogOpen = false
    }

    LaunchedEffect(Unit) {
        viewModel.getBooks()
    }
    Scaffold(
        topBar = {
            BooksTopBar()
        },
        floatingActionButton = {
            AddBookFloatingActionButton(
                openDialog = {
                    openDialog()
                }
            )
        },
        content = { padding ->
            BooksContent(
                padding = padding,
                navigateToUpdateBookScreen = navigateToUpdateBookScreen
            )
            if(isDialogOpen) {
                AddBookAlertDialog(
                    closeDialog = {
                        closeDialog()
                    },
                    addBook = { book ->
                        viewModel.addBook(book)
                    }
                )
            }
        }
    )
}