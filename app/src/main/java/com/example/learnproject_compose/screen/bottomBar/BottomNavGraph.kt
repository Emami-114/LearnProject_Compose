package com.example.learnproject_compose.screen.bottomBar

import QuizPage
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learnproject_compose.dictionary.presentation.DictionaryPages
import com.example.learnproject_compose.dictionary.presentation.savedWords.SavedWordPage
import com.example.learnproject_compose.dictionary.presentation.wordData.WordDataPage
import com.example.learnproject_compose.screen.SearchPage
import com.example.learnproject_compose.deutschTest.peresentation.DeutsclandPage
import com.example.learnproject_compose.screen.home.HomeScreen
import com.example.learnproject_compose.screen.profile.ProfilePage
import com.example.learnproject_compose.deutschTest.peresentation.quiz.QuizCounterPage
import com.example.learnproject_compose.screen.setting.SettingPage
import com.example.learnproject_compose.signIn_signUp.presentation.Profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraphBotton(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.SignInScreen.route,
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None }
    ) {
        composable(route = BottomBarScreen.Home.route) { HomeScreen() }
        composable(route = BottomBarScreen.Search.route) { SearchPage(navController = navHostController) }
        composable(route = BottomBarScreen.Search.route) { SearchPage(navController = navHostController) }
        composable(route = BottomBarScreen.Profile.route) { ProfilePage(navController = navHostController) }
        composable(route = BottomBarScreen.Setting.route) { SettingPage(navController = navHostController) }
        composable(route = BottomBarScreen.Deutsc.route) { DeutsclandPage(navController = navHostController) }
        composable(route = BottomBarScreen.QuizDisplay.route) { QuizPage(navController = navHostController) }
        composable(route = BottomBarScreen.QuizDisplayCounter.route) { QuizCounterPage(navController = navHostController) }
        composable(route = BottomBarScreen.DictionaryScreenSearch.route) {
            WordDataPage(
                navController = navHostController
            )
        }

        composable(route = BottomBarScreen.DictionaryScreenFavorite.route) {
            SavedWordPage(
                navController = navHostController
            )
        }

        composable(route = BottomBarScreen.DictionaryPages.route) {
            DictionaryPages(
                navController = navHostController
            )
        }



        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }

    }
}