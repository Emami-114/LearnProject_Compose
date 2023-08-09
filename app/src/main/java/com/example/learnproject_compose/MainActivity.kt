package com.example.learnproject_compose

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.loadingAnimation.LoadingAnimation
import com.example.learnproject_compose.deutschTest.peresentation.DeQuizViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWord.AddMissWordViewModel
import com.example.learnproject_compose.navigation.AuthScreen
import com.example.learnproject_compose.navigation.Graph
import com.example.learnproject_compose.navigation.RootNavigation
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.screen.home.slider.SliderViewModel
import com.example.learnproject_compose.signIn_signUp.presentation.LoginViewModel
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val loginViewModel by viewModels<LoginViewModel>()
    private val deQuizViewModel by viewModels<DeQuizViewModel>()
    private val sliderViewModel by viewModels<SliderViewModel>()


    @RequiresApi(Build.VERSION_CODES.R)
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
        "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val window = rememberWindowSizeClass()

            LearnProject_ComposeTheme(window) {
                val context = LocalContext.current

                Log.d("MYTAG", "SliderViewModel: " + sliderViewModel.wordsList.data.toString())


                var hasNotificationPermission by remember {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        mutableStateOf(
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) == PackageManager.PERMISSION_GRANTED
                        )
                    } else mutableStateOf(true)
                }


                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = DeepBlue,
                        darkIcons = false
                    )
//                    systemUiController.isNavigationBarVisible = false
//                     systemUiController.isSystemBarsVisible = false
//                     window.setFlags(
//                     WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                     WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//                     )

                }

//                Log.d(
//                    "MYTAG",
//                    "Meine Activity:   " + missingWordViewModel.missWordCatUiState.value.missingWorCatdList.data.toString()
//                )


                Surface {

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = {
                            hasNotificationPermission = it
                        }
                    )

                    LaunchedEffect(key1 = Unit) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }

//                    Button(onClick = {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
//                        }
//                    }) {
//                        Text(text = "heaaaikmjij")
//
//                    }

                    navController = rememberNavController()
                    RootNavigation(navController = navController)

                    AuthState(navController, loginViewModel)


                }


            }
        }
    }

}


@Composable
private fun AuthState(navController: NavController, loginViewModel: LoginViewModel) {
    val isUserSigneOut = loginViewModel.getAuthState().collectAsState().value
    if (isUserSigneOut) {
        NavigateToSignInScreen(navController = navController)
    } else {
        if (loginViewModel.isEmailVerfied) {
            NavigateToProfileScreen(navController)
        } else {
            NavigateToVerifyEmailScreen(navController)
        }
    }

}

@Composable
private fun NavigateToSignInScreen(navController: NavController) =
    navController.navigate(AuthScreen.Login.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

@Composable
private fun NavigateToProfileScreen(navController: NavController) =
    navController.navigate(Graph.HOME) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

@Composable
private fun NavigateToVerifyEmailScreen(navController: NavController) =
    navController.navigate(AuthScreen.Verify.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }





