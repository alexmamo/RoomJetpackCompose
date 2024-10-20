package ro.alexmamo.roomjetpackcompose.navigation

import kotlinx.serialization.Serializable

@Serializable
object BooksScreen

@Serializable
data class UpdateBookScreen(
    val id: Int,
    val title: String,
    val author: String
)