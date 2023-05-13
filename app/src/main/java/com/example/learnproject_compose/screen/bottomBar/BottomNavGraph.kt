package com.example.learnproject_compose.screen.bottomBar

import QuizPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learnproject_compose.dictionary.presentation.DictionaryPages
import com.example.learnproject_compose.dictionary.presentation.savedWords.SavedWordPage
import com.example.learnproject_compose.dictionary.presentation.wordData.WordDataPage
import com.example.learnproject_compose.screen.SearchPage
import com.example.learnproject_compose.screen.deutscland.DeutsclandPage
import com.example.learnproject_compose.screen.home.HomeScreen
import com.example.learnproject_compose.screen.profile.ProfilePage
import com.example.learnproject_compose.screen.quiz.QuizCounterPage
import com.example.learnproject_compose.screen.setting.SettingPage

@Composable
fun SetupNavGraphBotton(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) { HomeScreen(navController = navHostController) }
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

    }
}