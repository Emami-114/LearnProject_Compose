package com.example.learnproject_compose.pushNotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import com.example.learnproject_compose.MainActivity
import com.example.learnproject_compose.R
import com.example.learnproject_compose.pushNotification.core.NotificationConstance
import com.example.learnproject_compose.pushNotification.core.Preferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class FirebaseCloudMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var shp: SharedPreferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        shp.edit().putString(Preferences.TOKEN, token).apply()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
    }

    private fun showNotification(message: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification =
            NotificationCompat.Builder(this, NotificationConstance.NOTIFICATION_CHANNEL_ID)
                .setContentTitle(message.data["title"])
                .setContentText(message.data["message"])
                .setSmallIcon(R.drawable.quizapp4)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        manager.notify(Random.nextInt(), notification)
    }

}