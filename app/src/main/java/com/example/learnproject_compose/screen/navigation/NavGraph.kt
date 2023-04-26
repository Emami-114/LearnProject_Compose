package com.example.learnproject_compose.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.deutscland.DeutsclandPage

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route) {
        composable(route = Screen.UberDeutscland.route) { DeutsclandPage(navController = navHostController) }
    }

}