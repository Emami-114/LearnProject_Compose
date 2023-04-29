package com.example.learnproject_compose.components.quizComponent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun QuestionTracker(
    counter: Int = 10,
    totalCount: Int = 100,
    bigTextColor: Color = Color.Black,
    counterText: String = "Frage ",
    totalCountTextColor: Color = Color.Gray,

    ) {

    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = bigTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp,
            )
        ) {
            append("$counterText $counter/")

            withStyle(
                style = SpanStyle(
                    color = totalCountTextColor, fontWeight = FontWeight.Medium, fontSize = 17.sp
                )
            ) {
                append("$totalCount")
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun QuestionTrackerPreview() {
    QuestionTracker()
}