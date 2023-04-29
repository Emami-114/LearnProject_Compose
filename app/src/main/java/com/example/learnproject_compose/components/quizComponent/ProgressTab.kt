package com.example.learnproject_compose.components.quizComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.ui.theme.Beige1
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet2
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.ButtonBlue

@Composable
fun ProgressTab(score: Float = 0f, totalQuiz: Float = 10f) {
    val procent = (score / totalQuiz) * 100
    val progressFactory = remember { mutableStateOf(procent * 0.0099f) }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        ButtonBlue,
                        BlueViolet1, ButtonBlue
                    )
                ), shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(25))
            .background(Color.Transparent)
    ) {

        Button(
            onClick = { /*TODO*/ },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(progressFactory.value)
                .background(
                    brush = Brush.linearGradient(
                        listOf(ButtonBlue, ButtonBlue)
                    )
                ),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {

        }


    }

}

@Preview(showBackground = true)
@Composable
fun ProgressTabPreview() {
    ProgressTab()
}