package com.example.learnproject_compose.signIn_signUp.presentation.verify_email

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.TopBar
import com.example.learnproject_compose.signIn_signUp.presentation.Profile.ProfileViewModel
import com.example.learnproject_compose.signIn_signUp.presentation.Profile.RevokeAccess
import com.example.learnproject_compose.signIn_signUp.presentation.signUp.SignUpViewModel
import com.example.learnproject_compose.signIn_signUp.presentation.verify_email.components.ReloadUser
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.TextWhite

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigationToProfileScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(topBar = {
        TopBar(title = "E-Mail bestätigen", signOut = {
            viewModel.signOut()
        }, revokeAccess = { viewModel.revokeAccess() })
    }, content = { padding ->
        VerifyEmailContent(padding = padding, reloadUser = {
            viewModel.reloadUser()
//            if (viewModel.isEmailVerified) {

                viewModel.loginFirebaseTofireStore()
//            }
        })
    }, scaffoldState = scaffoldState)

    ReloadUser(navigateToProfileScreen = {
        if (viewModel.isEmailVerified) {
            navigationToProfileScreen()
        } else {
            com.example.learnproject_compose.signIn_signUp.util.Toast.showToast(
                context,
                "Ihre E-Mail-Adresse ist nicht bestätigt."
            )
        }
    })

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = { viewModel.signOut() })

}

@Composable
fun VerifyEmailContent(
    viewModel: SignUpViewModel = hiltViewModel(),
    padding: PaddingValues,
    reloadUser: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize().background(largeRadialGradient)
            .padding(padding)
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                reloadUser()

            },
            text = "Bereits verifiziert?",
            style = MaterialTheme.typography.h6,
            color = TextWhite,
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Wenn nicht, prüfen Sie bitte auch den Spam-Ordner.",
            style = MaterialTheme.typography.subtitle1,
            color = TextWhite,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(modifier = Modifier.width(250.dp),
            onClick = { viewModel.sendEmailVerfication() },
            colors = ButtonDefaults.buttonColors(backgroundColor = LightGreen1)
        ) {
            Text(text = "Email nochmal senden", color = TextWhite, style = MaterialTheme.typography.h6)

        }
    }
}