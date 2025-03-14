package ro.alexmamo.roomjetpackcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ro.alexmamo.roomjetpackcompose.core.BOOK_TABLE
import ro.alexmamo.roomjetpackcompose.navigation.BookDetails

@Entity(tableName = BOOK_TABLE)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String
)

fun Book.toBookDetails() = BookDetails(
    id = this.id,
    title = this.title,
    author = this.author
)