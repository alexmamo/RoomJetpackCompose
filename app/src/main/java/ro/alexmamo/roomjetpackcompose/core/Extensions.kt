package ro.alexmamo.roomjetpackcompose.core

import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.navigation.UpdateBook

fun Book.toUpdateBook() = UpdateBook(
    id = this.id,
    title = this.title,
    author = this.author
)

fun UpdateBook.toBook() = Book(
    id = this.id,
    title = this.title,
    author = this.author
)