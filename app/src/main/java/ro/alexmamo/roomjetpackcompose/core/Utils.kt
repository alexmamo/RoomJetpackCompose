package ro.alexmamo.roomjetpackcompose.core

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText

fun toastMessage(
    context: Context,
    message: String
) = makeText(context, message, LENGTH_LONG).show()