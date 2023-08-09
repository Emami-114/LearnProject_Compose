package com.example.learnproject_compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BarrScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BarrScreen(
        route = "HOME_route",
        title = "HOME",
        icon = Icons.Rounded.Home
    )

    object Dictionary : BarrScreen(
        route = "dictiobary_page2",
        title = "PROFILE",
        icon = Icons.Rounded.Book
    )

    object Setting : BarrScreen(
        route = "SETTING_route",
        title = "Setting",
        icon = Icons.Rounded.Settings
    )

    object Search : BarrScreen(
        route = "SEARCH_route",
        title = "SEARCH",
        icon = Icons.Rounded.Search
    )
}
