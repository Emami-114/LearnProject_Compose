package com.example.learnproject_compose.screen.navigation

sealed class Screen(val route: String) {
    object UberDeutscland : Screen(route = "deutschland_screen")
}