package com.example.learnproject_compose.signIn_signUp.presentation.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.example.learnproject_compose.signIn_signUp.repository.SendPasswordResetEmailResponses
import com.example.learnproject_compose.signIn_signUp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {
    var sendPasswordResetEmailResponses by mutableStateOf<SendPasswordResetEmailResponses>(
        Response.Success(
            false
        )
    )

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        sendPasswordResetEmailResponses = Response.Loading
        sendPasswordResetEmailResponses = repo.sendPasswordResetEmail(email)
    }
}