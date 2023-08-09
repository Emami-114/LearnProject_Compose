package com.example.learnproject_compose.signIn_signUp.presentation.signUp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.model.LoginModele
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.example.learnproject_compose.signIn_signUp.repository.SendEmailVerificationResponses
import com.example.learnproject_compose.signIn_signUp.repository.SignUpResponses
import com.example.learnproject_compose.signIn_signUp.util.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {


    var signUpResponse by mutableStateOf<SignUpResponses>(Response.Success(false))
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponses>(
        Response.Success(false)
    )
        private set


    fun signUpWithEmailAndPassword(useName: String, email: String, password: String) =
        viewModelScope.launch {
            signUpResponse = Response.Loading
            signUpResponse = repo.firebaseSignUpWithEmailAndPassword(useName,email, password)


        }


    fun sendEmailVerfication() = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()


    }


}