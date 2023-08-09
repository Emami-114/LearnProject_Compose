package com.example.learnproject_compose.signIn_signUp.presentation.forgot_password.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.EmailField
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.backgroundWhite

@Composable
fun ForgotPasswordContent(padding: PaddingValues, sendPasswordResetEmail: (email: String) -> Unit) {
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(email = email, onEmailValueChange = { newValue -> email = newValue })
        Spacer(modifier = Modifier.height(30.dp))
        Button(colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
            contentPadding = PaddingValues(AppTheme.dimens.small),
            shape = RoundedCornerShape(AppTheme.dimens.small),
            modifier = Modifier.width(250.dp),
            onClick = { sendPasswordResetEmail(email.text) }) {
            Text(
                text = "Zurücksetzen",
                style = MaterialTheme.typography.h6,
                color = TextWhite,
                fontWeight = FontWeight.Medium
            )
        }
    }

}