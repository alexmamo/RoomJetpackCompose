package ro.alexmamo.roomjetpackcompose.core

class Constants {
    companion object {
        //Room
        const val BOOK_TABLE = "book_table"

        //Screens
        const val BOOKS_SCREEN = "Books"
        const val UPDATE_BOOK_SCREEN = "Update book"

        //Arguments
        const val ID = "id"

        //Actions
        const val ADD_BOOK = "Add a book."
        const val EDIT_BOOK = "Edit a book"
        const val DELETE_BOOK = "Delete a book."

        //Buttons
        const val ADD_BUTTON = "Add"
        const val DISMISS_BUTTON = "Dismiss"
        const val UPDATE_BUTTON = "Update"

        //Placeholders
        const val BOOK_TITLE = "Type a book title..."
        const val AUTHOR = "Type the author name..."
        const val EMPTY_STRING = ""

        //Messages
        const val EMPTY_TITLE_MESSAGE = "Title cannot be empty."
        const val EMPTY_AUTHOR_MESSAGE = "Author cannot be empty."
    }
}