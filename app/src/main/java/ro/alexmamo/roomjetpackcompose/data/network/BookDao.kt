package ro.alexmamo.roomjetpackcompose.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.*
import kotlinx.coroutines.flow.Flow
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.BOOK_TABLE
import ro.alexmamo.roomjetpackcompose.domain.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM $BOOK_TABLE ORDER BY id ASC")
    fun getBooks(): Flow<List<Book>>

    @Query("SELECT * FROM $BOOK_TABLE WHERE id = :id")
    fun getBook(id: Int): Flow<Book>

    @Insert(onConflict = IGNORE)
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}