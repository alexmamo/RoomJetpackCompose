package ro.alexmamo.roomjetpackcompose.utils

import android.content.Context
import ro.alexmamo.roomjetpackcompose.R
import ro.alexmamo.roomjetpackcompose.domain.model.Book

fun getBookTest(context: Context) = Book(
    id = 1,
    title = context.getString(R.string.title_test),
    author = context.getString(R.string.author_test)
)

fun getUpdatedBookTest(context: Context) = getBookTest(context).copy(
    title = context.getString(R.string.new_title_test)
)