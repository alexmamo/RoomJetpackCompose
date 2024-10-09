package ro.alexmamo.roomjetpackcompose.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import ro.alexmamo.roomjetpackcompose.core.Constants.Companion.TAG

fun printError(e: Exception) = Log.e(TAG, "${e.message}")

fun toastMessage(
    context: Context,
    message: String
) = makeText(context, message, LENGTH_LONG).show()