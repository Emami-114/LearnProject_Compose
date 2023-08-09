package com.example.learnproject_compose.signIn_signUp.presentation.signUp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.signIn_signUp.componente.ProgressBar
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.SignUpViewModel
import com.example.learnproject_compose.signIn_signUp.util.Response

@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> sendEmailVerificationResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}