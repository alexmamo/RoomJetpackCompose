package ro.alexmamo.roomjetpackcompose.navigation

import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOKS_SCREEN
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.UPDATE_BOOK_SCREEN

sealed class Screen(val route: String) {
    data object BooksScreen: Screen(BOOKS_SCREEN)
    data object UpdateBookScreen: Screen(UPDATE_BOOK_SCREEN)

    fun withArgs(vararg args: Int) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}