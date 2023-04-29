package com.example.learnproject_compose.screen.deutscland

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.circle.CirclerScreen
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.model.QuizModel
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.home.features.FeaturesItem
import com.example.learnproject_compose.screen.home.features.features
import kotlinx.coroutines.delay

@Composable
fun DeutsclandPage(navController: NavController) {
    val value = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = value) {
        delay(500)
        value.value = 150
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CirclerScreen(value.value, 300)








        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {
                    navController.navigate(route = BottomBarScreen.QuizDisplay.route)

                }
            }

            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

            }

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {

                    navController.navigate(BottomBarScreen.Deutsc.route)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

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