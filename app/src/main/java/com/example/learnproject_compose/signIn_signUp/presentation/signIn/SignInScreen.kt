package com.example.learnproject_compose.signIn_signUp.presentation.signIn

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.signIn_signUp.componente.ProgressBar
import com.example.learnproject_compose.signIn_signUp.presentation.signIn.components.SignInContent
import com.example.learnproject_compose.signIn_signUp.util.Response
import com.example.learnproject_compose.signIn_signUp.util.Toast
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigationToSignUpScreen: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(topBar = {
        SignInTopBar()
    }, content = { padding ->
        SignInContent(
            padding = padding,
            signIn = { email, password ->
                viewModel.signInWithEmailAndPassword(email, password)
            },
            navigateToSignUpScreen = navigationToSignUpScreen,
            navigationToForgetPassword = navigateToForgotPasswordScreen
        )

    }, contentWindowInsets = WindowInsets(bottom = 20.dp))

    SignIn(showErrorMessage = { errorMessage ->
        Toast.showToast(context, errorMessage)
    })

}

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when (val signInResponse = viewModel.signInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}


@Composable
fun SignInTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Anmeldung",
                color = TextWhite, style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium

            )
        }, backgroundColor = DeepBlue
    )
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun PreviewSignIn() {

    SignInContent(
        padding = PaddingValues(5.dp),
        signIn = { email, password ->
        },
        navigateToSignUpScreen = {},
        navigationToForgetPassword = {}
    )
}