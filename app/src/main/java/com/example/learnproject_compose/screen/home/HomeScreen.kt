package com.example.learnproject_compose.screen.home

//import com.example.learnproject_compose.screen.home.features.FeaturesItem
import MultiFloatingActionButton
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.navigation.HomeNavGraph
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
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val viewModel2: MissingWordViewModel = hiltViewModel()

    val navItem = listOf(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.haus), label = "Home", route = "HOME2"
        ) {
            navController.navigate(DetailsScreen.HOME2.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.karte),
            label = "Karten Übungen",
            route = "cardsSetList"
        ) {
            navController.navigate(DetailsScreen.CardsSetList.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.quiz),
            label = "Quiz Übungen",
            route = "addMissWordCatPreview"
        ) {
            navController.navigate(DetailsScreen.AddMissWordCatPreview.route)
        },

        NavigationDrawerItem(
            image = painterResource(id = R.drawable.sprache),
            label = "Wörterbuch",
            route = "dictionaryPages"
        ) {
            navController.navigate(DetailsScreen.DictionaryPages.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.quiztest),
            label = "Einbürgerungtest",
            route = "DEUTSCHTEST"
        ) {
            navController.navigate(DetailsScreen.DeutscTest.route)
        },

        NavigationDrawerItem(painterResource(id = R.drawable.ausloggen), label = "Abmelden") {
            viewModel.signOut()
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.mulleimer),
            textColor = Color.Red,
            label = "Konto Löschen"
        ) { viewModel.revokeAccess() }

    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Log.d("MYTAG", "currenDestination: ${currentDestination?.route}")

    Scaffold(
        modifier = Modifier,


        ) {
        if (AppTheme.orientation == Orientation.Portroit) {
            HomeNavGraph(navController = navController)

        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(largeRadialGradient)
            ) {

                Column(modifier = Modifier.weight(2.5f)) {
                    DrawerVertikalContent(
                        windows = rememberWindowSizeClass(),
                        navItem = navItem, navDestination = currentDestination,
                        userEmail = viewModel2.usersUiState?.email ?: ""
                    ) {

                    }
                }
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .background(Color.Transparent)
                ) {
                    HomeNavGraph(navController = navController)

                }

            }
        }

    }


}


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavHostController) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val missingWordUser: MissingWordViewModel = hiltViewModel()
    val sliderViewModel = hiltViewModel<SliderViewModel>()



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
    val navItem = listOf(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.haus), label = "Home"
        ) {
            navController.navigate(DetailsScreen.HOME2.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.karte), label = "Karten Übungen"
        ) {
            navController.navigate(DetailsScreen.CardsSetList.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.quiz), label = "Quiz Übungen"
        ) {
            navController.navigate(DetailsScreen.AddMissWordCatPreview.route)
        },

        NavigationDrawerItem(
            image = painterResource(id = R.drawable.sprache), label = "Wörterbuch"
        ) {
            navController.navigate(DetailsScreen.DictionaryPages.route)
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.quiztest), label = "Einbürgerungtest"
        ) {
            navController.navigate(DetailsScreen.DeutscTest.route)
        },

        NavigationDrawerItem(painterResource(id = R.drawable.ausloggen), label = "Abmelden") {
            viewModel.signOut()
        },
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.mulleimer),
            textColor = Color.Red,
            label = "Konto Löschen"
        ) { viewModel.revokeAccess() }

    )

    if (AppTheme.orientation == Orientation.Portroit) {

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

                    }, fabOption = FabOption(iconTint = TextWhite, showLabel = false)
                )
            }

        },
            drawerContent = {
                DrawerContent(
                    navItem = navItem,
                    userEmail = missingWordUser.usersUiState?.email ?: ""
                ) {
                    coroutineScope.launch {
                        delay(250)

                        scaffoldState.drawerState.close()
                    }
                }


            },
            drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
            drawerElevation = 20.dp,
            topBar = {
                HomeTopAppBar {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(largeRadialGradient)
                    .padding(it)
                    .padding(AppTheme.dimens.small)
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

    } else {


        HomeVertikal(navController = navController, navItem = navItem)


    }


}


val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF09685F), DeepBlue),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(
    userName: String = "",
) {
    val currentTime = LocalTime.now()
    val targetTime6 = LocalTime.of(6, 0)
    val targetTime10 = LocalTime.of(10, 0)
    val targetTime18 = LocalTime.of(18, 0)
    val greeting = if (currentTime.isAfter(targetTime6) && currentTime.isBefore(targetTime10)) {
        "Guten Morgen"
    } else if (currentTime.isAfter(targetTime10) && currentTime.isBefore(targetTime18)) {
        "Guten Tag"
    } else {
        "Guten Abend"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "$greeting $userName!",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )

        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewHome() {
    Home(navController = rememberNavController())
}


@Composable
fun HomeTopAppBar(onNavIconClick: () -> Unit) {
    androidx.compose.material.TopAppBar(backgroundColor = DeepBlue,
        title = {
            Text(
                text = "Home",
                color = TextWhite,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavIconClick) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(AppTheme.dimens.mediumLarge)
                )
            }
        })
}