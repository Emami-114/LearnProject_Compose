package com.example.learnproject_compose.components.quizComponent

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.model.QuizModel
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.LightRed
import com.example.learnproject_compose.viewModel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun QuestionDisplay(
    viewModel: MainViewModel,
    quizModel: QuizModel,
    questionIndex: MutableState<Int>,
    onNextClick: () -> Unit
) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
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




        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            var isActiveSelected by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(5.dp))
//            ProgressTab(
//                viewModel.questionIndex.value.toFloat(),
//                viewModel.quizList.value.size.toFloat()
//            )

            GradientProgressbar1(
                viewModel.questionIndex.value.toFloat(),
                viewModel.quizList.value.size.toFloat()
            )


            QuestionTracker(viewModel.questionIndex.value + 1, viewModel.quizList.value.size)

            DrawDottedLine()

            QuestionTitle(quizModel = quizModel, textSize = 25.sp)
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
                        if (correctOrNot.value == false) {
                            viewModel.update(false, quizModel.id)


                        } else {
                            viewModel.update(true, quizModel.id)
                        }
                    }
                }
            }




            nextBtn(corOrNot = correctOrNot.value, index = questionIndex) {
                isActiveSelected = false
                correctOrNot.value = null

                onNextClick()

            }


        }

    }

}

@Composable
fun nextBtn(corOrNot: Boolean?, index: MutableState<Int>, onClick: () -> Unit) {
    LaunchedEffect(key1 = corOrNot) {
        if (corOrNot != null) {
            delay(1000)
            index.value = index.value + 1
            onClick()
        }
    }
}





@Preview(showBackground = true)
@Composable
fun AnswerViewPreview() {

}