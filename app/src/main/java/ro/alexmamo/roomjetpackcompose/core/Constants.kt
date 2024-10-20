package ro.alexmamo.roomjetpackcompose.core

class Constants {
    companion object {
        //App
        const val TAG = "AppTag"

        //Room
        const val BOOK_TABLE = "book_table"

        //Screens
        const val BOOKS_SCREEN = "Books"
        const val UPDATE_BOOK_SCREEN = "Update book"

        //Actions
        const val INSERT_BOOK = "Insert a book."

        //Buttons
        const val INSERT_BUTTON = "Insert"
        const val DISMISS_BUTTON = "Dismiss"
        const val UPDATE_BUTTON = "Update"

        //Content Descriptions
        const val EDIT_ITEM = "Edit item"
        const val DELETE_ITEM = "Delete item"
        const val NAVIGATE_BACK = "Navigate back"

        //Placeholders
        const val BOOK_TITLE = "Type a book title..."
        const val AUTHOR_NAME = "Type the author name..."
        const val EMPTY_STRING = ""

        //Messages
        const val EMPTY_BOOK_LIST_MESSAGE = "The book list is empty."
        const val EMPTY_TITLE_MESSAGE = "Title cannot be empty."
        const val EMPTY_AUTHOR_MESSAGE = "Author cannot be empty."
        const val NO_UPDATES_MESSAGE = "No updates performed."
    }
}