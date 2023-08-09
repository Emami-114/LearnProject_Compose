package com.example.learnproject_compose.pushNotification

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextBlack2
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.ui.theme.backgroundWhite
import com.example.learnproject_compose.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(closeDialog: () -> Unit) {
    val viewModel = hiltViewModel<NotificationViewModel>()


    AlertDialog(modifier = Modifier.padding(10.dp),
        containerColor = backgroundWhite,
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = closeDialog,
        title = {
            Text(
                text = "Push Benachrichtigung",
                fontWeight = FontWeight.Medium, color = TextBlack
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = "Überschrift") },
                    value = viewModel.title,
                    singleLine = true,
                    onValueChange = viewModel::onTitleChange,
                    placeholder = {
                        Text(
                            text = "Überschrift"
                        )
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LightGreen3,
                        focusedLabelColor = LightGreen3,
                        unfocusedBorderColor = TextBlack2,
                        unfocusedLabelColor = TextBlack2,
                        textColor = TextBlack,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    label = { Text(text = "Nachricht") },
                    value = viewModel.message,
                    onValueChange = viewModel::onMessageChange,
                    placeholder = {
                        Text(
                            text = "Nachricht"
                        )
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LightGreen3,
                        focusedLabelColor = LightGreen3,
                        unfocusedBorderColor = TextBlack2,
                        unfocusedLabelColor = TextBlack2,
                        textColor = TextBlack,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.onSendNotification()
                    closeDialog()
                },
                colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Senden", color = TextWhite)
            }

        },
        dismissButton = {
            Button(
                onClick = { closeDialog.invoke() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(
                    .9.dp,
                    LightGreen3
                ), shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Abbrechen", color = TextBlack)
            }
        })


}

