package com.example.learnproject_compose.screen.deutscland

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.circle.CirclerScreen
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.model.QuizModel
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.home.features.Features
import com.example.learnproject_compose.screen.home.features.FeaturesItem
import com.example.learnproject_compose.screen.home.features.features
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet2
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.Color1Index1
import com.example.learnproject_compose.ui.theme.Color1Index2
import com.example.learnproject_compose.ui.theme.Color1Index3
import com.example.learnproject_compose.ui.theme.Color2Index1
import com.example.learnproject_compose.ui.theme.Color2Index2
import com.example.learnproject_compose.ui.theme.Color2Index3
import com.example.learnproject_compose.ui.theme.Color3Index1
import com.example.learnproject_compose.ui.theme.Color3Index2
import com.example.learnproject_compose.ui.theme.Color3Index3
import com.example.learnproject_compose.ui.theme.Color4Index1
import com.example.learnproject_compose.ui.theme.Color4Index2
import com.example.learnproject_compose.ui.theme.Color4Index3
import com.example.learnproject_compose.viewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun DeutsclandPage(navController: NavController) {
    val viewModel: MainViewModel = hiltViewModel()
    val value = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = value) {
        delay(500)
        value.value = 150
    }

    Column(
        modifier = Modifier
            .fillMaxSize().background(largeRadialGradient)
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CirclerScreen(value.value, 300)

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    features = Features(
                        title = "Ohne Title",
                        viewModel.quizList.value.size, Color1Index1, Color1Index2,
                        Color1Index3
                    )
                ) {
                    navController.navigate(route = BottomBarScreen.QuizDisplay.route)
//                    viewModel.getAllQuiz()


                }
            }

            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    features = Features(
                        title = "Ohne Title",
                        viewModel.filterList.value.size, Color2Index1, Color2Index2,
                        Color2Index3
                    )
                ) {
                    navController.navigate(route = BottomBarScreen.QuizDisplay.route)
//                    viewModel.getSucsses()


                }

            }

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    features = Features(
                        title = "Ohne Title",
                        0, Color3Index1, Color3Index2,
                        Color3Index3
                    )
                ) {

                    navController.navigate(BottomBarScreen.QuizDisplayCounter.route)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    features = Features(
                        title = "Ohne Title",
                        0, Color4Index1, Color4Index2,
                        Color4Index3
                    )
                ) {}

            }

        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}


@Preview
@Composable
fun DeutschlandPreview() {

    DeutsclandPage(navController = rememberNavController())
}