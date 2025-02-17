package ro.alexmamo.roomjetpackcompose.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import ro.alexmamo.roomjetpackcompose.domain.model.Response

const val TAG = "AppTag"
const val BOOK_TABLE = "book_table"

fun printError(e: Exception) = Log.e(TAG, "${e.message}")

fun showMessage(
    context: Context,
    resourceId: Int
) = makeText(context, context.resources.getString(resourceId), LENGTH_LONG).show()

suspend fun <T> launchCatching(block: suspend () -> T) = try {
    Response.Success(block())
} catch (e: Exception) {
    Response.Failure(e)
}