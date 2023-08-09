package com.example.learnproject_compose.deutschTest.peresentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.circle.CirclerScreen
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.features.Features
import com.example.learnproject_compose.screen.home.features.FeaturesItem
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.screen.home.slider.SliderColor
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.Beige1
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
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
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.OrangeYellow1
import com.example.learnproject_compose.ui.theme.OrangeYellow2
import com.example.learnproject_compose.ui.theme.OrangeYellow3
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.util.noRippleClickable
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DeutsclandPage(navController: NavController) {
    val viewModel: DeQuizViewModel = hiltViewModel()
    val value = remember { mutableIntStateOf(0) }
    val value2 = remember { mutableIntStateOf(0) }
    val value3 = remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = value) {
        delay(400)
        value.intValue =
            viewModel.deQuizAllFromDatabase.count { it.isCorOrNot == true || it.isCorOrNot == false }
        value2.intValue = viewModel.deQuizAllFromDatabase.count { it.isCorOrNot == true }
        value3.intValue = viewModel.deQuizAllFromDatabase.count { it.isCorOrNot == false } ?: 0
    }



    Scaffold(topBar = {
        TopBarSelb(title = "Einbürgerungtest") {
            navController.popBackStack(DetailsScreen.HOME2.route, false)
        }
    }) { paddingValue ->

        val featuresColor = listOf<SliderColor>(
            SliderColor(
                Color1Index1, Color1Index2, Color1Index3
            ),
            SliderColor(
                Color2Index1, Color2Index2, Color2Index3
            ),
            SliderColor(
                Color3Index1, Color3Index2, Color3Index3
            ),
            SliderColor(
                Color4Index1, Color4Index2, Color4Index3
            ),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .background(largeRadialGradient)
                .padding(
                    vertical = AppTheme.dimens.small, horizontal =

                    if (AppTheme.orientation == Orientation.Landscope)
                        AppTheme.dimens.mediumLarge else AppTheme.dimens.small
                )

                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CirclerScreen(
                value.intValue,
                correctIndicatorValue = value2.value,
                failureIndicatorValue = value3.value,
                310
            )

            DropDownDeQuiz(viewModel)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.small),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    FeaturesItem(
                        featureTitle = "Allgemeine Fragen",
                        featureDesc = "310 Fragen",
                        featuresColor[3]
                    ) {
                        navController.navigate(route = DetailsScreen.QuizDisplay.route)
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    FeaturesItem(
                        featureTitle = "Letzte Fehler",
                        featureDesc = "${viewModel.deQuizAllFromDatabase.count { it.isCorOrNot == false } ?: 0} Fragen",
                        featuresColor[2]
                    ) {
                        navController.navigate(route = DetailsScreen.DeQuizDisplayFalse.route)
//                    viewModel.getAllCoreOrNot(0)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.small),

                ) {
                Box(modifier = Modifier.weight(1f)) {
                    FeaturesItem(
                        featureTitle = "Probeprüfung", featureDesc = "33 Fragen", featuresColor[1]
                    ) {

                        navController.navigate(DetailsScreen.QuizDisplayCounter.route)
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    FeaturesItem(
                        featureTitle = "Markierte Fragen",
                        featureDesc = "${viewModel.deQuizAllFromDatabase.count { it.isFavors == true } ?: 0} Fragen",
                        featuresColor[0]
                    ) {
                        navController.navigate(DetailsScreen.QuizDisplayFavorite.route)

                    }

                }

            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}


@Preview
@Composable
fun DeutschlandPreview() {
//    DeutsclandPage(navController = rememberNavController())
}