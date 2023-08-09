package com.example.learnproject_compose.signIn_signUp.presentation.signIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.EmailField
import com.example.learnproject_compose.signIn_signUp.componente.PasswordField
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInContent(
    padding: PaddingValues,
    signIn: (email: String, password: String) -> Unit,
    navigationToForgetPassword: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                ""
            )
        )
    }
    var password by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue("")
        )
    }

    val keyboard = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
            .verticalScroll(state = rememberScrollState())
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(email = email, onEmailValueChange = { newValue -> email = newValue })
        Spacer(modifier = Modifier.height(15.dp))
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue -> password = newValue })
        Spacer(modifier = Modifier.height(30.dp))

        Button(colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
            contentPadding = PaddingValues(AppTheme.dimens.small),
            shape = RoundedCornerShape(AppTheme.dimens.small),modifier = Modifier.width(250.dp),onClick = {
            keyboard?.hide()
            signIn(email.text, password.text)
        }) {
            Text(text = "Anmelden", style = MaterialTheme.typography.h6, color = TextWhite, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(AppTheme.dimens.mediumLarge))

        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.clickable { navigationToForgetPassword() },
                text = "Passwort vergessen?",
                color = TextWhite,
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.medium))

            Text(
                modifier = Modifier.clickable { navigateToSignUpScreen() },
                text = "Kein Konto? Registrieren Sie sich.", color = TextWhite,
                style = MaterialTheme.typography.body1,
            )

        }

    }


}