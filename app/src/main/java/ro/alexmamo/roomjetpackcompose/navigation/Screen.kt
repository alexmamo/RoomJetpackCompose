package ro.alexmamo.roomjetpackcompose.navigation

import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOKS_SCREEN
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE_BOOK_SCREEN

sealed class Screen(val route: String) {
    object BooksScreen: Screen(BOOKS_SCREEN)
    object UpdateBookScreen: Screen(UPDATE_BOOK_SCREEN)
}