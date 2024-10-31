package ro.alexmamo.roomjetpackcompose.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import ro.alexmamo.roomjetpackcompose.domain.model.Response

fun printError(e: Exception) = Log.e(TAG, "${e.message}")

fun showToastMessage(
    context: Context,
    message: String
) = makeText(context, message, LENGTH_LONG).show()

suspend fun <T> launchCatching(block: suspend () -> T) = try {
    Response.Success(block())
} catch (e: Exception) {
    Response.Failure(e)
}