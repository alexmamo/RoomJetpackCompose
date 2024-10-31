package ro.alexmamo.roomjetpackcompose.navigation

import kotlinx.serialization.Serializable
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Serializable
object Books

@Serializable
data class UpdateBook(
    val id: Int,
    val title: String,
    val author: String
)

fun UpdateBook.toBook() = Book(
    id = this.id,
    title = this.title,
    author = this.author
)