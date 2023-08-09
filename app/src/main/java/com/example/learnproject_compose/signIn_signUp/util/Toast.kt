package com.example.learnproject_compose.signIn_signUp.util

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText

class Toast {

    companion object {

        fun print(e: Exception) = Log.e("MYTAG", e.stackTraceToString())

        fun showToast(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()
    }

}