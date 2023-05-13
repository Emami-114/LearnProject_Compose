package com.example.learnproject_compose.screen.quiz

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.components.quizComponent.GradientProgressbar1
import com.example.learnproject_compose.components.quizComponent.ProgressTab
import com.example.learnproject_compose.components.quizComponent.QuestionTitle
import com.example.learnproject_compose.components.quizComponent.QuestionTracker
import com.example.learnproject_compose.components.quizComponent.SelectableAnimate
import com.example.learnproject_compose.components.quizComponent.nextBtn
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.LightRed
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.viewModel.MainViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizCounterPage(navController: NavController) {
    val viewModel: MainViewModel = hiltViewModel()
    val quizModel = viewModel.quizList.value[viewModel.questionIndex.value]

    val counter = CountDownQuiz(totalTimeSecound = 3600) {
        navController.popBackStack()
    }


    Column(
        modifier = Modifier
            .fillMaxSize().background(TextWhite)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val answerIndex = remember { mutableStateOf<Int>(0) }
        val correctOrNot = remember { mutableStateOf<Boolean?>(null) }
        var answerList =
            mutableStateOf(
                listOf(

                    quizModel.answer,
                    quizModel.answer2,
                    quizModel.answer3,
                    quizModel.answerCorrect

                )
            ).value


        val updateAnswer: (Int) -> Unit = remember(quizModel) {
            {
                answerIndex.value = it
                val answerQual = answerList[it] == quizModel.answerCorrect
                correctOrNot.value = answerQual

            }
        }


        var isActiveSelected by remember { mutableStateOf(false) }



        Spacer(modifier = Modifier.height(5.dp))
        GradientProgressbar1(counter.toFloat(),3600f, scoreString = FormatSecounds(counter))


        QuestionTracker(viewModel.questionIndex.value + 1, 33)

        DrawDottedLine()


        QuestionTitle(quizModel = quizModel, textSize = 22.sp)
        Spacer(modifier = Modifier.height(20.dp))

        answerList.forEachIndexed { index, s ->
            var select by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = select) {
                delay(500)
                select = false
            }



            SelectableAnimate(
                selected = select,
                quizAnswer = s,
                isActiveState = isActiveSelected,
                titleColor = if (correctOrNot.value == true && index == answerIndex.value) LightGreen3 else if (correctOrNot.value == false && index == answerIndex.value) LightRed else Color.Gray,
                iconColor = if (correctOrNot.value == true && index == answerIndex.value) Color.Green else if (correctOrNot.value == false && index == answerIndex.value) Color.Red else Color.Gray,
                borderColor = if (correctOrNot.value == true && index == answerIndex.value) Color.Green else if (correctOrNot.value == false && index == answerIndex.value) Color.Red else Color.Gray,


                ) {
                select = !select
                isActiveSelected = true
                updateAnswer(index)
//                    viewModel.insert(quizModel)
                if (correctOrNot.value != null) {
                    if (correctOrNot.value == true) {
                        viewModel.update(true, quizModel.id)
                    } else {
                        viewModel.update(false, quizModel.id)

                    }
                }
            }
        }





        nextBtn(corOrNot = correctOrNot.value, index = viewModel.questionIndex) {
            isActiveSelected = false
            correctOrNot.value = null
//                        viewModel.checkAnswer()
            if (viewModel.questionIndex.value >= viewModel.quizList.value.size-1){
                navController.popBackStack()
            }

        }

    }
}

@Composable
fun CountDownQuiz(totalTimeSecound: Long, onTimeFinishid: () -> Unit): Long {
    var remainTimeInSecound by remember { mutableStateOf(totalTimeSecound) }

    LaunchedEffect(Unit) {
        while (remainTimeInSecound > 0) {
            delay(1000)
            remainTimeInSecound -= 1
        }
        onTimeFinishid()
    }
//    FormatSecounds(secound = remainTimeInSecound)
    return remainTimeInSecound
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun FormatSecounds(secound: Long): String {
    val minute = TimeUnit.SECONDS.toMinutes(secound) % 60
    val secoundRemaining = secound % 60
      return "%02d:%02d".format(minute, secoundRemaining)
}