package com.example.learnproject_compose.components.quizComponent

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.learnproject_compose.R

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.peresentation.DeQuizViewModel
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.AquaBlue
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.LightRed
import com.example.learnproject_compose.ui.theme.TextWhite
import com.google.accompanist.pager.ExperimentalPagerApi

import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun QuestionDisplay(
    viewModel: DeQuizViewModel,
    quizModel: DeQuiz,
    maxQuiz: Int?,
    answerList: List<String?>,
    questionIndex: MutableState<Int>,
    paddingValues: PaddingValues,
    onNextClick: () -> Unit
) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState()
            )

    ) {

        val answerIndex = remember { mutableIntStateOf(0) }
        val correctOrNot = remember { mutableStateOf<Boolean?>(null) }

        val updateAnswer: (Int) -> Unit = remember(quizModel) {
            {
                answerIndex.value = it
                val answerQual = answerList[it] == quizModel.corAnswer
                correctOrNot.value = answerQual

            }
        }




        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(paddingValues)

                .padding(AppTheme.dimens.smallMedium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.smallMedium),
            horizontalAlignment = Alignment.Start
        ) {
            var isActiveSelected by remember { mutableStateOf(false) }



            GradientProgressbar1(
                questionIndex.value.toFloat(),
                maxQuiz?.toFloat(),
                bigTextColor = TextWhite
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.weight(8f)) {
                    QuestionTitle(quizModel = quizModel, textcolor = TextWhite)

                }


            }
//            DrawDottedLine()
            quizModel.url?.let {
                coilImageLoader(it)
            }



            Spacer(modifier = Modifier.height(10.dp))

            answerList.forEachIndexed { index, s ->
                var select by remember { mutableStateOf(false) }
                LaunchedEffect(key1 = select) {
                    delay(500)
                    select = false
                }

                val correctAnswerIndex = answerList.indexOf(quizModel.corAnswer)
                SelectableAnimate(
                    selected = select,
                    quizAnswer = s,
                    isActiveState = isActiveSelected,
                    titleColor = if (correctOrNot.value == true && index == answerIndex.value || correctOrNot.value == false && correctAnswerIndex == index) GreenColor else if (correctOrNot.value == false && index == answerIndex.value) LightRed else TextWhite,
                    iconColor = if (correctOrNot.value == true && index == answerIndex.value || correctOrNot.value == false && correctAnswerIndex == index) GreenColor else if (correctOrNot.value == false && index == answerIndex.value) LightRed else TextWhite,
                    borderColor = if (correctOrNot.value == true && index == answerIndex.value || correctOrNot.value == false && correctAnswerIndex == index) GreenColor else if (correctOrNot.value == false && index == answerIndex.value) LightRed else Color.Gray,
                    icon = if (correctOrNot.value == true && index == answerIndex.value || correctOrNot.value == false && correctAnswerIndex == index) painterResource(
                        id = R.drawable.ic_check_circle_24
                    ) else if (correctOrNot.value == false && index == answerIndex.value) painterResource(
                        id = R.drawable.ic_cancel_24
                    ) else painterResource(id = R.drawable.ic_circle_24),


                    ) {
                    select = !select
                    isActiveSelected = true
                    updateAnswer(index)
//                    viewModel.insert(quizModel)
                    if (correctOrNot.value != null) {
                        if (correctOrNot.value == false) {
                            viewModel.updateDeQuiz(quizModel.id, false)

                        } else {
                            viewModel.updateDeQuiz(quizModel.id, true)
                        }

                    }
                }


            }





            nextBtn2(corOrNot = correctOrNot.value, index = questionIndex) {
                isActiveSelected = false
                correctOrNot.value = null

                onNextClick()

            }


            Spacer(modifier = Modifier.height(50.dp))
        }


    }

}




@Composable
fun nextBtn(corOrNot: Boolean?, index: MutableState<Int>, onClick: () -> Unit) {
    LaunchedEffect(key1 = corOrNot) {
        if (corOrNot != null) {
            delay(1500)
            index.value = index.value + 1
            onClick()
        }
    }
}


@Composable
fun nextBtn2(corOrNot: Boolean?, index: MutableState<Int>, onClick: () -> Unit) {
    val visible = corOrNot != null
//    AnimatedVisibility(
//        visible = visible,
//        enter = fadeIn(animationSpec = tween(500)) + slideInVertically(animationSpec = tween(500)),
//        exit = fadeOut(animationSpec = tween(200)) + slideOutVertically(
//            animationSpec = tween(
//                200,
//                easing = LinearOutSlowInEasing
//            )
//        )
//    ) {


        Row(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent),
                border =  BorderStroke(
                    1.dp,
                    LightGreen3
                ),
                modifier = Modifier.weight(1f),
                enabled = index.value > 0,
                shape = RoundedCornerShape(AppTheme.dimens.small),
                onClick = {
                    if (index.value > 0) {
                        index.value = index.value - 1
                        onClick()
                    }

                }) {
                Text(
                    text = "Vorherige",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(AppTheme.dimens.small), enabled = visible,  border =  BorderStroke(
                    1.dp,
                    LightGreen3
                ),
                onClick = {
                    index.value = index.value + 1
                    onClick()

                }) {
                Text(
                    text = "Nächste",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }

        }

//    }
}


@Composable
fun coilImageLoader(url: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        val painter =
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .size(Size.ORIGINAL)
                    .crossfade(1000)
                    .scale(Scale.FILL)
                    .build()
            )

        Image(
            painter = painter, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(180.dp)
        )

    }
}