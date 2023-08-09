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

    object QuizDisplayCounter :
        BottomBarScreen(route = "quizPageCounter", title = "QuizCounter", icon = R.drawable.ic_setting)


    object DictionaryScreenSearch :
        BottomBarScreen(route = "dictionaryScreenSearch", title = "Dictionary Search", icon = R.drawable.ic_setting)

    object DictionaryScreenFavorite :
        BottomBarScreen(route = "dictionaryScreenFavorite", title = "Dictionary Favorite", icon = R.drawable.ic_setting)

    object DictionaryPages :
        BottomBarScreen(route = "dictionaryPages", title = "Dictionary", icon = R.drawable.ic_setting)


    object SignInScreen :
        BottomBarScreen(route = "signInScreen", title = "Sign In", icon = R.drawable.ic_setting)

    object ForgotPasswordScreen :
        BottomBarScreen(route = "forgotPasswordScreen", title = "Forgot Password", icon = R.drawable.ic_setting)
    object SignUpPages :
        BottomBarScreen(route = "signUpPages", title = "Sign Up", icon = R.drawable.ic_setting)

    object VerifyEmailScreen :
        BottomBarScreen(route = "verifyEmailScreen", title = "Verify Screen", icon = R.drawable.ic_setting)

    object ProfileScreen :
        BottomBarScreen(route = "profileScreen", title = "Profile", icon = R.drawable.ic_setting)
}