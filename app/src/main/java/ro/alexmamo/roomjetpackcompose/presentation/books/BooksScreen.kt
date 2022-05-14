package ro.alexmamo.roomjetpackcompose.presentation.books

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ro.alexmamo.roomjetpackcompose.presentation.books.components.AddBookAlertDialog
import ro.alexmamo.roomjetpackcompose.presentation.books.components.AddBookFloatingActionButton
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksContent
import ro.alexmamo.roomjetpackcompose.presentation.books.components.BooksTopBar

@Composable
@ExperimentalMaterialApi
fun BooksScreen(
    navController: NavController,
    viewModel: BooksViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getBooks()
    }
    Scaffold(
        topBar = {
            BooksTopBar()
        },
        floatingActionButton = {
            AddBookFloatingActionButton()
        },
        content = { padding ->
            BooksContent(
                padding = padding,
                navController = navController
            )
        }
    )
    if(viewModel.openDialog) {
        AddBookAlertDialog()
    }
}