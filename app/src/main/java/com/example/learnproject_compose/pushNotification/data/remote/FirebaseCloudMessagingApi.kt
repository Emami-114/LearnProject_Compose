package com.example.learnproject_compose.pushNotification.data.remote

import com.example.learnproject_compose.pushNotification.data.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseCloudMessagingApi {
    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
        private const val FCM_KEY =
            "AAAA11Zq2CE:APA91bFV_q9FFo4RFLQZYtvISZ13hytlQqRwxZFaZkSAhuqz4tsnvQgKsgPQmprAtWVjtQ7FAtU77ARn0UdEz6d4bRNWcfRtNKJD9iDHuK4V7xjyNhDEl9WtcLVQneyaD0eDdLalMUv6"
    }

    @POST("fcm/send")
    @Headers("Authorization: key=${FCM_KEY}", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}