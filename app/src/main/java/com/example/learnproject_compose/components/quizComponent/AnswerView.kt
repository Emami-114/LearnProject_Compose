package com.example.learnproject_compose.components.quizComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.model.QuizModel

@Composable
fun QuestionDisplay(quizModel: QuizModel) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val answerList = remember { quizModel.answer.toMutableList() }

        val answerIndex = remember { mutableStateOf<Int>(0) }
        val correctOrNot = remember { mutableStateOf<Boolean?>(null) }


        val updateAnswer: (Int) -> Unit = remember(quizModel) {
            {
                answerIndex.value = it
                val answerQual = answerList[it] == quizModel.answerCorrect
                correctOrNot.value = answerQual
            }
        }


        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            var isActiveSelected by remember { mutableStateOf(false) }
            ProgressTab()
            QuestionTracker(counter = 10, totalCount = 50)

            DrawDottedLine()

            QuestionTitle(quizModel = quizModel, textSize = 25.sp)
            Spacer(modifier = Modifier.height(20.dp))



            quizModel.answer.forEachIndexed { index, s ->
                var select by remember { mutableStateOf(false) }

                SelectableAnimate(
                    selected = select,
                    quizAnswer = s,
                    isActiveState = isActiveSelected,
                    titleColor = if (correctOrNot.value == true && index == answerIndex.value) Color.Green else if (correctOrNot.value == false && index == answerIndex.value) Color.Red else Color.Gray,
                    iconColor = if (correctOrNot.value == true && index == answerIndex.value) Color.Green else if (correctOrNot.value == false && index == answerIndex.value) Color.Red else Color.Gray,
                    borderColor = if (correctOrNot.value == true && index == answerIndex.value) Color.Green else if (correctOrNot.value == false && index == answerIndex.value) Color.Red else Color.Gray,


                    ) {
                    select = !select
                    isActiveSelected = true
                    updateAnswer(index)

                }

            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun AnswerViewPreview() {
    QuestionDisplay(
        quizModel = QuizModel(
            1, "Das ist erste Quiz Frage", "Das ist Richtige Antwort",
            listOf(
                "Das ist Falsche Antwort 1",
                "Das ist Falsche Antwort 2",
                "Das ist Falsche Antwort 3",
                "Das ist Richtige Antwort",
            )
        )
    )
}