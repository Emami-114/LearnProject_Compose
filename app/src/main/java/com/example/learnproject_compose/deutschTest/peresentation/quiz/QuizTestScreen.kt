package com.example.learnproject_compose.deutschTest.peresentation.quiz

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.components.quizComponent.GradientProgressbar1
import com.example.learnproject_compose.components.quizComponent.GradientProgressbar2
import com.example.learnproject_compose.components.quizComponent.QuestionTitle
import com.example.learnproject_compose.components.quizComponent.QuestionTracker
import com.example.learnproject_compose.components.quizComponent.SelectableAnimate
import com.example.learnproject_compose.components.quizComponent.nextBtn
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.peresentation.DeQuizViewModel
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.Color3Index3
import com.example.learnproject_compose.ui.theme.DarkRed
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuizCounterPage(navController: NavController) {
    val viewModel: DeQuizViewModel = hiltViewModel()

    val quizDeQuizList = viewModel.deQuizAllFromDatabase.shuffled().take(10)

    val currentquestion = try {
        quizDeQuizList?.get(viewModel.questionIndex.intValue % quizDeQuizList.size)

    } catch (e: Exception) {
        null
    } ?: DeQuiz()


    val quizTestResult = viewModel.quizTestResult

    var openDialog by remember { mutableStateOf(false) }

    val answerList = listOf(
        currentquestion.answer2,
        currentquestion.answer3,
        currentquestion.answer1,
        currentquestion.corAnswer
    ).shuffled()

    val deMarket = mutableStateOf(currentquestion.isFavors ?: false)

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepBlue),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(8f)) {

                    TopBarSelb(title = "Probeprüfung") {
                        navController.popBackStack()
                    }
                }
                Row(modifier = Modifier.weight(2f)) {
                    IconButton(onClick = {
                        deMarket.value = !deMarket.value
                        viewModel.updateDeQuizFavorite(currentquestion?.id, deMarket.value)
                    }) {
                        Icon(
                            imageVector = if (deMarket.value) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                            contentDescription = null, tint = TextWhite, modifier = Modifier.size(
                                AppTheme.dimens.mediumLarge
                            )
                        )


                    }
                }
            }

        }, contentWindowInsets = WindowInsets(AppTheme.dimens.small)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .verticalScroll(state = rememberScrollState())
                .padding(it)

        ) {


            DeQuizCounterScreen(
                navController = navController,
                viewModel = viewModel,
                quizModel = currentquestion,
                answerList = answerList,
                questionIndex = viewModel.questionIndex,
                quizTestResult = quizTestResult,
                totalQuiz = quizDeQuizList?.size ?: 0
            ) {
                if (viewModel.questionIndex.intValue > quizDeQuizList.size - 1) {
                    viewModel.questionIndex.intValue -= 1
                    openDialog = true
                }

            }


            AnimatedVisibility(visible = openDialog) {
                AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.small),
                    onDismissRequest = { openDialog = false },
                    title = {
                        Text(
                            text = "Ergebnis",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            color = TextBlack,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center)

                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppTheme.dimens.smallMedium),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            DrawDottedLine()
                            Spacer(modifier = Modifier.height(5.dp))
//                            Text(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                text = if (quizTestResult > 17) "Glück wünsch" else "Gib nich Auf!",
//                                style = MaterialTheme.typography.h5,
//                                fontWeight = FontWeight.Bold,
//                                color = if (quizTestResult > 17) GreenColor else DarkRed
//                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = if (quizTestResult.intValue > 17) "Sie haben bestanden" else "Sie haben nicht bestanden!",
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                color = if (quizTestResult.intValue > 17) GreenColor else DarkRed
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = if (quizTestResult.intValue > 17) "Sie haben von 33 Fragen ${quizTestResult.intValue} richtig beantwortet. "
                                else "Sie haben von 33 Fragen nur ${quizTestResult.intValue} richtig beantwortet. Sie müssen mindestens 17 Fragen richtig beantworten. Üben Sie mehr und machen Sie den Test erneut.",
                                style = MaterialTheme.typography.body2
                            )


                        }


                    },
                    confirmButton = {
                        Button(contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp),
                            shape = RoundedCornerShape(AppTheme.dimens.small),
                            colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
                            onClick = {
//                                navController.navigate(DetailsScreen.QuizDisplayCounter.route)
                                viewModel.questionIndex.intValue = 0
                                openDialog = false
                            }) {

                            Text(
                                text = "Wiederholen",
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                navController.navigate(DetailsScreen.DeutscTest.route)
                                openDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(
                                top = 5.dp, bottom = 5.dp, start = 12.dp, end = 12.dp
                            ),
                            border = BorderStroke(.9.dp, LightGreen3)
                        ) {
                            Text(
                                text = "Zurück",
                                color = TextBlack,
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Medium
                            )

                        }
                    },
                    containerColor = TextWhite,
                    shape = RoundedCornerShape(AppTheme.dimens.small),
                    properties = DialogProperties(dismissOnClickOutside = false)

                )
            }

        }

    }


}

@Composable
fun DeQuizCounterScreen(
    navController: NavController,
    viewModel: DeQuizViewModel,
    quizModel: DeQuiz,
    answerList: List<String?>,
    questionIndex: MutableState<Int>,
    quizTestResult:MutableState<Int>,
    totalQuiz: Int,
    onFinish: () -> Unit
) {


    Column(
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.smallMedium),
        horizontalAlignment = Alignment.Start
    ) {
        val answerIndex = remember { mutableIntStateOf(0) }
        val correctOrNot = remember { mutableStateOf<Boolean?>(null) }


        val updateAnswer: (Int) -> Unit = remember(quizModel) {
            {
                answerIndex.intValue = it
                val answerQual = answerList[it] == quizModel.corAnswer
                correctOrNot.value = answerQual

            }
        }


        var isActiveSelected by remember { mutableStateOf(false) }




        Spacer(modifier = Modifier.height(5.dp))
        GradientProgressbar1(
            questionIndex.value.toFloat(),
            total = totalQuiz.toFloat(),
            bigTextColor = TextWhite
        )
        Spacer(modifier = Modifier.height(5.dp))



        QuestionTitle(quizModel = quizModel, textcolor = TextWhite)
        DrawDottedLine(color = LightGreen3)


        Spacer(modifier = Modifier.height(15.dp))

        answerList.forEachIndexed { index, s ->
            var select by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = select) {
                delay(1500)
                select = false
            }

            SelectableAnimate(
                selected = select,
                quizAnswer = s,
                isActiveState = isActiveSelected,
                titleColor = if (select) Color3Index3 else TextWhite,
                iconColor = if (select) Color3Index3 else TextWhite,
                borderColor = if (select) Color3Index3 else Color.Gray,
                icon = if (select) painterResource(
                    id = R.drawable.ic_check_circle_24
                ) else painterResource(id = R.drawable.ic_circle_24),
            ) {
                select = !select
                isActiveSelected = true
                updateAnswer(index)
//                    viewModel.insert(quizModel)
                if (correctOrNot.value != null) {
                    if (correctOrNot.value == true) {
                        quizTestResult.value = quizTestResult.value + 1

                        viewModel.updateDeQuiz(quizModel.id, true)

                    } else {

                        viewModel.updateDeQuiz(quizModel.id, false)

                    }
                }
            }
        }





        nextBtn(corOrNot = correctOrNot.value, index = viewModel.questionIndex) {
            isActiveSelected = false
            correctOrNot.value = null
//                        viewModel.checkAnswer()

            onFinish()
        }

    }

}



