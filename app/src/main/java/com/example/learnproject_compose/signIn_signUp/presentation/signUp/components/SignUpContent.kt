package com.example.learnproject_compose.signIn_signUp.presentation.signUp.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.EmailField
import com.example.learnproject_compose.signIn_signUp.componente.PasswordField
import com.example.learnproject_compose.signIn_signUp.componente.UserNameField
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    padding: PaddingValues,
    signUp: (userName: String, email: String, password: String) -> Unit,
    navigationBack: () -> Unit
) {

    var userName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                ""
            )
        )
    }
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

        UserNameField(userName = userName, onUserNameValueChange = { newValue ->
            userName = newValue
        })
        Spacer(modifier = Modifier.height(15.dp))

        EmailField(email = email, onEmailValueChange = { newValue ->
            email = newValue
        })

        Spacer(modifier = Modifier.height(15.dp))

        PasswordField(password = password, onPasswordValueChange = { newValue ->
            password = newValue
        })

        Spacer(modifier = Modifier.height(25.dp))

        Button(colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
            contentPadding = PaddingValues(AppTheme.dimens.small),
            shape = RoundedCornerShape(AppTheme.dimens.small), modifier = Modifier.width(250.dp),onClick = {
            keyboard?.hide()
            signUp(userName.text,email.text, password.text)
        }) {
            Text(text = "Registrieren", style = MaterialTheme.typography.h6, color = TextWhite, fontWeight = FontWeight.Medium)

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier.clickable { navigationBack() },
            text = "Bereits Benutzer? Anmelden.",
            style = MaterialTheme.typography.body1,
            color = TextWhite
        )


    }

}


