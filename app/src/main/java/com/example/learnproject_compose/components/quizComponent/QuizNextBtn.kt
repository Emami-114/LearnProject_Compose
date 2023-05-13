package com.example.learnproject_compose.components.quizComponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    btnText: String = "Nächste",
    visivlity: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = visivlity,
        ) {


            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = btnText)

            }

        }
    }

}

