package ro.alexmamo.roomjetpackcompose.navigation

import kotlinx.serialization.Serializable

@Serializable
object Books

@Serializable
data class UpdateBook(
    val id: Int,
    val title: String,
    val author: String
)