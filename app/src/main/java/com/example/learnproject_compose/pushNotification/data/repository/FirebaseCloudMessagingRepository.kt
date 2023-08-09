package com.example.learnproject_compose.pushNotification.data.repository

import com.example.learnproject_compose.pushNotification.data.model.PushNotification
import com.example.learnproject_compose.pushNotification.data.remote.FirebaseCloudMessagingApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class FirebaseCloudMessagingRepository @Inject constructor(private val api: FirebaseCloudMessagingApi) {

    suspend fun postNotification(notification: PushNotification): Response<ResponseBody> {
        return api.postNotification(notification)
    }
}