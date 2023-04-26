package com.example.learnproject_compose.screen.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learnproject_compose.screen.SearchPage
import com.example.learnproject_compose.screen.deutscland.DeutsclandPage
import com.example.learnproject_compose.screen.home.HomeScreen
import com.example.learnproject_compose.screen.navigation.Screen
import com.example.learnproject_compose.screen.profile.ProfilePage
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

    }
}