package com.example.learnproject_compose.screen.home

import MultiFloatingActionButton
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learnproject_compose.R
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.pushNotification.NotificationScreen
import com.example.learnproject_compose.screen.home.drawerMenu.DrawerContent
import com.example.learnproject_compose.screen.home.drawerMenu.DrawerVertikalContent
import com.example.learnproject_compose.screen.home.drawerMenu.NavigationDrawerItem
import com.example.learnproject_compose.screen.home.features.FeaturesSection
import com.example.learnproject_compose.screen.home.multiFloatingButtom.FabIcon
import com.example.learnproject_compose.screen.home.multiFloatingButtom.FabOption
import com.example.learnproject_compose.screen.home.multiFloatingButtom.MultiFabItem
import com.example.learnproject_compose.screen.home.slider.SlideUi
import com.example.learnproject_compose.screen.home.slider.SliderColor
import com.example.learnproject_compose.screen.home.slider.SliderViewModel
import com.example.learnproject_compose.screen.home.slider.SliderWordModel
import com.example.learnproject_compose.signIn_signUp.presentation.Profile.ProfileViewModel
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.Beige1
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet2
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.OrangeYellow1
import com.example.learnproject_compose.ui.theme.OrangeYellow2
import com.example.learnproject_compose.ui.theme.OrangeYellow3
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeVertikal(
    navController: NavHostController,
    navItem: List<NavigationDrawerItem>,
    userEmail: String = ""
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val missingWordUser: MissingWordViewModel = hiltViewModel()
    val sliderViewModel = hiltViewModel<SliderViewModel>()
    val windows = rememberWindowSizeClass()

    val context = LocalContext.current
    var openDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()


    val sliderColor = listOf<SliderColor>(
        SliderColor(
            OrangeYellow1, OrangeYellow2, OrangeYellow3
        ),
        SliderColor(
            LightGreen1, LightGreen2, LightGreen3
        ),
        SliderColor(
            BlueViolet1, BlueViolet2, BlueViolet3
        ),
        SliderColor(
            Beige1, Beige2, Beige3
        ),
    )




    Scaffold(scaffoldState = scaffoldState, floatingActionButton = {
        if (missingWordUser.usersUiState?.rolle == "Admin") {

            MultiFloatingActionButton(
                items = listOf(

                    MultiFabItem(
                        id = 2,
                        iconRes = R.drawable.send,
                        label = "Push Nachrichten",
                    ) {
                        openDialog = true
                    },
                    MultiFabItem(
                        id = 2,
                        iconRes = R.drawable.karte,
                        label = "Karteikarten",
                    ) {
                        navController.navigate(DetailsScreen.AddCardsSet.route)
                    },
                    MultiFabItem(
                        id = 1,
                        iconRes = R.drawable.quiz,
                        label = "Quiz",
                    ) {
                        navController.navigate(DetailsScreen.AddMissWordCat.route)
                    },

                    ),
                fabIcon = FabIcon(iconRes = R.drawable.ic_add, iconRotate = 128f),
                onFabClicked = {
                    openDialog = true

                }, fabOption = FabOption(iconTint = TextWhite, showLabel = false)
            )
        }
    },

        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        drawerElevation = 20.dp,
        topBar = {

//            HomeVertikalTopAppBar()
        }
    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize().background(largeRadialGradient)
                    .padding(it)
                    .padding(vertical = AppTheme.dimens.small, horizontal = AppTheme.dimens.mediumLarge)
                    .verticalScroll(rememberScrollState())
            ) {
                if (openDialog) {
                    NotificationScreen {
                        openDialog = false
                    }
                }

                Greeting(userName = missingWordUser.usersUiState?.name ?: "")
                SlideUi(
                    sliderViewModel.wordsList.data?.randomOrNull() ?: SliderWordModel(),
                    sliderColor = sliderColor[1]
                )

                FeaturesSection(navController)
                Spacer(modifier = Modifier.height(AppTheme.dimens.large))

            }
        }
    }



@Composable
private fun HomeVertikalTopAppBar() {
    androidx.compose.material.TopAppBar(
        backgroundColor = DeepBlue,
        title = {
            Text(
                text = "Home",
                color = TextWhite,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Medium
            )
        },
    )
}