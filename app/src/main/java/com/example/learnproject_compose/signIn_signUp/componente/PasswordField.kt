package com.example.learnproject_compose.signIn_signUp.componente

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke

@Composable
fun PasswordField(
    password: TextFieldValue,
    onPasswordValueChange: (newValue: TextFieldValue) -> Unit
) {
    var passwordIsVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue ->
            onPasswordValueChange(newValue)
                        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = null, tint = TextWhite)},
        label = {
            Text(text = "Password", style = androidx.compose.material.MaterialTheme.typography.subtitle1)
                },
        singleLine = true,
        visualTransformation = if (passwordIsVisible){ VisualTransformation.None }else { PasswordVisualTransformation()},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (passwordIsVisible){
                Icons.Filled.Visibility
            }else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordIsVisible = !passwordIsVisible}) {
                Icon(imageVector = icon, contentDescription = null, tint = TextWhite)

            }
},  colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightGreen1
            ,
            focusedLabelColor = LightGreen1,
            unfocusedBorderColor = TextWhiteDarke,
            unfocusedLabelColor = TextWhiteDarke,
            textColor = TextWhite,
            cursorColor = LightGreen1
        )
    )

}