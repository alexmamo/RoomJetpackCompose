package ro.alexmamo.roomjetpackcompose.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_TABLE
import ro.alexmamo.roomjetpackcompose.domain.model.Book
import ro.alexmamo.roomjetpackcompose.domain.repository.Books

@Dao
interface BookDao {
    @Query("SELECT * FROM $BOOK_TABLE ORDER BY id ASC")
    fun getBooks(): Flow<Books>

    @Query("SELECT * FROM $BOOK_TABLE WHERE id = :id")
    fun getBook(id: Int): Book

    @Insert(onConflict = IGNORE)
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}