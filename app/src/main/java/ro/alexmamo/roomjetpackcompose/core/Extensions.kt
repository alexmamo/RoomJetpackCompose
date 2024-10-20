package ro.alexmamo.roomjetpackcompose.core

import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.navigation.UpdateBookScreen

fun Book.toUpdateBookScreen() = UpdateBookScreen(
    id = this.id,
    title = this.title,
    author = this.author
)

fun UpdateBookScreen.toBook() = Book(
    id = this.id,
    title = this.title,
    author = this.author
)