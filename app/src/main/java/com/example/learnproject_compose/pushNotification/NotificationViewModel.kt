package com.example.learnproject_compose.pushNotification

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.pushNotification.core.NotificationConstance
import com.example.learnproject_compose.pushNotification.data.model.NotificationData
import com.example.learnproject_compose.pushNotification.data.model.PushNotification
import com.example.learnproject_compose.pushNotification.data.repository.FirebaseCloudMessagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: FirebaseCloudMessagingRepository) :
    ViewModel() {

    private var job: Job? = null

    var title by mutableStateOf("")
        private set
    var message by mutableStateOf("")
        private set
    var token by mutableStateOf("")
        private set

    fun onTitleChange(value: String) {
        title = value
    }

    fun onMessageChange(value: String) {
        message = value
    }

    fun onTokenChange(value: String) {
        token = value
    }

    fun onSendNotification() {
        if (title.isBlank() || message.isBlank()) return
        job?.cancel()

        job = viewModelScope.launch {
            try {
                val response = repository.postNotification(
                    PushNotification(
                        data = NotificationData(
                            title,
                            message
                        ), to = token.ifBlank { NotificationConstance.TOPIC }
                    ))
                if (response.isSuccessful) {
                    title = ""
                    message = ""
                    token = ""
                    println("NotificationViewModel: Success!")
                } else {
                    Log.d("MYTAG", "Notification ViewModel : Error!")
                }
            } catch (e: Exception) {
                Log.d("MYTAG", "Notification ViewModel: ${e.message}")
            }
        }
    }

}