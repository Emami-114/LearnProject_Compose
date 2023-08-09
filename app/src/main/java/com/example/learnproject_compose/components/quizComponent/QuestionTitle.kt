package com.example.learnproject_compose.components.quizComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.model.QuizModel
import com.example.learnproject_compose.ui.theme.AppTheme

@Composable
fun QuestionTitle(
    quizModel: DeQuiz ,
    textSize: TextStyle = MaterialTheme.typography.h5,
    textcolor: Color = Color.Black
) {

    Column {
        quizModel.question?.let {
            Text(
                text = it,
                Modifier
                    .padding(AppTheme.dimens.small)
                    .align(alignment = Alignment.Start)
                    .fillMaxWidth(),
                style = textSize,
                fontWeight = FontWeight.Medium,
                color = textcolor
            )
        }

    }
}

@Preview
@Composable
fun QuestionTitlePreview() {
}