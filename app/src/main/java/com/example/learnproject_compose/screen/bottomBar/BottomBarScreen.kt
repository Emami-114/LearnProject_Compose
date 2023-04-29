package com.example.learnproject_compose.screen.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.learnproject_compose.R

sealed class BottomBarScreen(val route: String, val title: String, val icon: Int) {

    object Home : BottomBarScreen(route = "home", title = "Home", icon = R.drawable.ic_home)
    object Search : BottomBarScreen(route = "search", title = "Search", icon = R.drawable.ic_search)
    object Profile :
        BottomBarScreen(route = "profile", title = "Profile", icon = R.drawable.ic_person)

    object Setting :
        BottomBarScreen(route = "setting", title = "Setting", icon = R.drawable.ic_setting)
    object Deutsc :
        BottomBarScreen(route = "deutsc", title = "Deutsch", icon = R.drawable.ic_setting)

    object QuizDisplay :
        BottomBarScreen(route = "quizPage", title = "Quiz", icon = R.drawable.ic_setting)
}