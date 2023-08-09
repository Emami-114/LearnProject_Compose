package com.example.learnproject_compose.signIn_signUp.presentation.signUp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.components.SendEmailVerification
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.components.SignUp
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.components.SignUpContent
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.components.SignUpTopBar
import com.example.learnproject_compose.signIn_signUp.util.Toast.Companion.showToast

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(contentColor = Color.White, topBar = {
        SignUpTopBar(navigateBack = navigateBack)
    }, content = { padding ->
        SignUpContent(padding = padding, signUp = { userName, email, password ->
            viewModel.signUpWithEmailAndPassword(userName, email, password)
        }, navigationBack = navigateBack)

    }, contentWindowInsets = WindowInsets(bottom = 20.dp))

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerfication()
        },
        showVerifyEmailMessage = {
            showToast(
                context,
                "Wir haben Ihnen eine E-Mail mit einem Link zur Bestätigung der E-Mail gesendet."
            )
        }
    )

    SendEmailVerification()

}