package com.example.learnproject_compose.pushNotification.core

import android.content.SharedPreferences

object Preferences {
    const val PREFS = "firebaseCloudMessagingPrefs"


    const val TOKEN = "token"
    const val DEFAULT_TOKEN = ""

}

fun SharedPreferences.token() =
    getString(Preferences.TOKEN, Preferences.DEFAULT_TOKEN) ?: Preferences.DEFAULT_TOKEN