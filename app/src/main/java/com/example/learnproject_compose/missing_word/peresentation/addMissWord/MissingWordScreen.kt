package com.example.learnproject_compose.missing_word.peresentation.addMissWord

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.components.loadingAnimation.LoadingAnimation
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.components.quizComponent.GradientProgressbar1
import com.example.learnproject_compose.components.quizComponent.SelectableAnimate
import com.example.learnproject_compose.components.quizComponent.nextBtn
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DarkRed
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.LightRed
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MissingOrQuizPage(navController: NavController, documentId: String) {
    val viewModel: AddMissWordViewModel = hiltViewModel()
    val missWordUiState = viewModel.missWordUiState.value
    var openDialog = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        try {
            viewModel.getMissWordFromFireStore(documentId)
        } catch (e: Exception) {
            Log.d("MYTAG", "MissingWord: ${e.cause}")
        }
    }

    val miss =
        missWordUiState.missingWordList.data?.get(viewModel.quizIndex.value % missWordUiState.missingWordList.data!!.size)
            ?: MissingWordModel()


    val answerList =
        listOf(
            miss.answer1, miss.answer2,
            miss.answer3, miss.corAnswer
        ).shuffled()


    Scaffold(
        topBar = {
            TopBarSelb(title = "Zurück") {
                navController.navigateUp()
            }
        }, contentWindowInsets = WindowInsets(AppTheme.dimens.small)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(paddingValues = it)
        ) {

            val context = LocalContext.current

            when (missWordUiState.missingWordList) {
                is Resources2.Loading -> {
                   LoadingAnimation()

                }

                is Resources2.Success -> {
                    AlertDialogMissing(openDialog, viewModel, navController)
                    MissingWordScreen(
                        viewModel = viewModel,
                        missingWordModel = miss,
                        quizIndex = viewModel.quizIndex,
                        totalCount = missWordUiState.missingWordList.data?.count(),
                        answerList = answerList
                    ) {

                        if (viewModel.quizIndex.value > (missWordUiState.missingWordList.data?.size?.minus(
                                1
                            )
                                ?: 0)
                        ) {
                            openDialog.value = true
                            viewModel.quizIndex.value -= 1
//                            navController.popBackStack()
                        }

                    }

                }

                is Resources2.Error -> {
                    Log.d("MYTAG", "MissingWordScreen: Error!")
                }


                else -> {}
            }


        }


    }

}

@Composable
fun MissingWordScreen(
    viewModel: AddMissWordViewModel,
    missingWordModel: MissingWordModel?,
    quizIndex: MutableState<Int>,
    totalCount: Int?,
    answerList: List<String?>?,
    onNextClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        val answerIndex = remember { mutableStateOf(0) }
        val correctOrNot = remember { mutableStateOf<Boolean?>(null) }

        val updateAnswer: (Int) -> Unit = remember(missingWordModel) {
            {
                answerIndex.value = it
                val answerQual = answerList?.get(it) == missingWordModel?.corAnswer
                correctOrNot.value = answerQual
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(AppTheme.dimens.smallMedium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.smallMedium),
            horizontalAlignment = Alignment.Start
        ) {

            var isActiveSelected by remember { mutableStateOf(false) }
            Spacer(modifier = Modifier.height(5.dp))

            GradientProgressbar1(
                score = quizIndex.value.toFloat(),
                total = totalCount?.toFloat(),
                bigTextColor = TextWhite
            )


            missingWordModel?.question?.let {
                Text(
                    text = it,
                    Modifier
                        .padding(AppTheme.dimens.small)
                        .align(alignment = Alignment.Start)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }
            DrawDottedLine()

            Spacer(modifier = Modifier.height(10.dp))

            answerList?.forEachIndexed { index, s ->
                var selected by remember { mutableStateOf(false) }

                LaunchedEffect(key1 = selected) {
                    delay(500)
                    selected = false
                }

                val correctAnswerIndex = answerList.indexOf(missingWordModel?.corAnswer)

                if (!s.isNullOrEmpty()) {


                    SelectableAnimate(
                        selected = selected,
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
                        selected = !selected
                        isActiveSelected = true
                        updateAnswer(index)
                        if (correctOrNot.value == true) {
                            viewModel.score.intValue += 1
                        }

                    }
                }


            }

            nextBtn(corOrNot = correctOrNot.value, index = quizIndex) {
                isActiveSelected = false
                correctOrNot.value = null
                onNextClick()

            }

            Spacer(modifier = Modifier.height(50.dp))

        }


    }


}

@Composable
private fun AlertDialogMissing(
    openDialog: MutableState<Boolean>,
    viewModel: AddMissWordViewModel,
    navController: NavController
) {
    val totalquiz = viewModel.missWordUiState.value.missingWordList.data?.count() ?: 0
    AnimatedVisibility(visible = openDialog.value) {

        AlertDialog(
            modifier = Modifier
                .padding(AppTheme.dimens.small)
                .fillMaxWidth(),
            onDismissRequest = { openDialog.value = false },
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Ergebnis",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = TextBlack,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.smallMedium),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

//                    DrawDottedLine()

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = if (viewModel.score.intValue > (totalquiz / 2)) "Gut gemacht!" else "Gib nicht auf!",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = if (viewModel.score.intValue > (totalquiz / 2)) GreenColor else DarkRed,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = viewModel.score.value.toString(),
                            style = MaterialTheme.typography.h6, fontWeight = FontWeight.Medium,
                            color = TextBlack
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "von",
                            style = MaterialTheme.typography.body2, fontWeight = FontWeight.Medium,
                            color = TextBlack
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "$totalquiz",
                            style = MaterialTheme.typography.h6, fontWeight = FontWeight.Medium,
                            color = TextBlack
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Du hast ${viewModel.score.value}  von ${totalquiz} Fragen richtig beantwortet. ",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Medium,
                        color = TextBlack,
                        textAlign = TextAlign.Center
                    )
                }


            },
            confirmButton = {
                Button(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                    contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp),
                    shape = RoundedCornerShape(AppTheme.dimens.small),
                    colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
                    onClick = {
                        viewModel.quizIndex.value = 0
                        openDialog.value = false
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
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 0.dp),
                    onClick = {
                        navController.navigateUp()
                        openDialog.value = false
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
            contentColor = TextWhite,
            backgroundColor = TextWhite,
            shape = RoundedCornerShape(AppTheme.dimens.small),
            properties = DialogProperties(dismissOnClickOutside = false)
        )
    }

}