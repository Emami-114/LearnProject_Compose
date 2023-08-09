package com.example.learnproject_compose.signIn_signUp.presentation.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.BackIcon
import com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.components.ForgotPassword
import com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.components.ForgotPasswordContent
import com.example.learnproject_compose.signIn_signUp.util.Toast
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ForgotPasswordTopBar(navigateBack = navigateBack)
        }, content = { padding ->

                ForgotPasswordContent(padding = padding, sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                })



        })

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            Toast.showToast(
                context,
                "Wir haben Ihnen eine E-Mail mit einem Link zum Zurücksetzen des Passworts gesendet."
            )
        },
        showErrorMessage = { errorMessage ->
            Toast.showToast(context, errorMessage)
        }
    )

}

@Composable
fun ForgotPasswordTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Passwort vergessen", color = TextWhite, style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }, backgroundColor = DeepBlue
    )
}