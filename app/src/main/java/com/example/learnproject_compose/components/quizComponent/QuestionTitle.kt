package com.example.learnproject_compose.components.quizComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.model.QuizModel

@Composable
fun QuestionTitle(
    quizModel: QuizModel ,
    textSize: TextUnit = 17.sp,
    textcolor: Color = Color.Black
) {

    Column {
        Text(
            text = quizModel.question,
            Modifier
                .padding(6.dp)
                .align(alignment = Alignment.Start)
                .fillMaxWidth(),
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            color = textcolor
        )

    }
}

@Preview
@Composable
fun QuestionTitlePreview() {
}