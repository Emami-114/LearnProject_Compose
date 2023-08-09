package com.example.learnproject_compose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.learnproject_compose.pushNotification.core.NotificationConstance
import com.example.learnproject_compose.ui.theme.TextWhite
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        subscribeTopic()

    }

    private fun subscribeTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic(NotificationConstance.TOPIC)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationConstance.NOTIFICATION_CHANNEL_ID,
                NotificationConstance.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Just to send notification, you know!"
                enableLights(true)
                lightColor = TextWhite.toArgb()
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}