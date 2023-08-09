package com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.signIn_signUp.componente.ProgressBar
import com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.ForgotPasswordViewModel
import com.example.learnproject_compose.signIn_signUp.util.Response

@Composable
fun ForgotPassword(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showResetPasswordMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when (val sendPasswordResetEmailResponses = viewModel.sendPasswordResetEmailResponses) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isPasswordResetEmailSend = sendPasswordResetEmailResponses.data
            LaunchedEffect(isPasswordResetEmailSend) {
                if (isPasswordResetEmailSend) {
                    navigateBack()
                    showResetPasswordMessage()
                }
            }
        }

        is Response.Failure -> sendPasswordResetEmailResponses.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }

}