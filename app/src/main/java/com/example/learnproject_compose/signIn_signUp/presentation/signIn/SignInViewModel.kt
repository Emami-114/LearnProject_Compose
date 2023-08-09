package com.example.learnproject_compose.signIn_signUp.presentation.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.example.learnproject_compose.signIn_signUp.repository.SignInResponses
import com.example.learnproject_compose.signIn_signUp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    var signInResponse by mutableStateOf<SignInResponses>(Response.Success(false))
        private set

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Response.Loading
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }

}