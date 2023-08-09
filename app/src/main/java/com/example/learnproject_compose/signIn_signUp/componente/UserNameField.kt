package com.example.learnproject_compose.signIn_signUp.componente

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import kotlinx.coroutines.job

@Composable
fun UserNameField(
    userName: TextFieldValue,
    onUserNameValueChange: (newValue: TextFieldValue) -> Unit
) {
    val focusRequester = FocusRequester()

    OutlinedTextField(
        value = userName,
        onValueChange = { newValue ->
            onUserNameValueChange(newValue)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null, tint = TextWhite
            )
        },
        label = {
            Text(
                text = "Nutzer Name", style = androidx.compose.material.MaterialTheme.typography.subtitle1
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier.focusRequester(focusRequester),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightGreen1
            ,
            focusedLabelColor = LightGreen1,
            unfocusedBorderColor = TextWhiteDarke,
            unfocusedLabelColor = TextWhiteDarke,
            textColor = TextWhite,
            cursorColor = LightGreen1
        )
    )

    LaunchedEffect(Unit) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }
}