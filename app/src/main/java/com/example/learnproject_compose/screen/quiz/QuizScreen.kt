package com.example.learnproject_compose.screen.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.model.QuizModel

@Composable
fun QuizPage() {


}

@Composable
fun QuizItem(quizModel: QuizModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(text = "12 Fragen von 30")
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Die Erste Frage Von Quiz App",
            fontSize = 25.sp, fontWeight = FontWeight.Bold)

    }
}

@Preview(showBackground = true)
@Composable
fun QuizPreview() {
    QuizItem(quizModel = QuizModel(1, "EinbürgerungTest", "1", "2", "3", "4"))
}