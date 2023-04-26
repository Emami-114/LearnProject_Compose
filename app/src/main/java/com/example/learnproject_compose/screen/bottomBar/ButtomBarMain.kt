package com.example.learnproject_compose.screen.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learnproject_compose.ui.theme.DarkerButtonBlue
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite


@Composable
fun BottomBar(navHostController: NavHostController) {

    val screen = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Profile,
        BottomBarScreen.Setting
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)),
        backgroundColor = DarkerButtonBlue, elevation = 600.dp,
    ) {
        screen.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
//        AnimatedVisibility(
//            visible = currentDestination?.id != 1,
//            enter = fadeIn() + slideInVertically(),
//            exit = fadeOut() + slideOutVertically()
//        ) {
//
//        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController,
) {
    val isSelected: Boolean = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    BottomNavigationItem(

        label = { Text(text = screen.title) },
        alwaysShowLabel = false,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
                tint = if (isSelected) LocalContentColor.current else LocalContentColor.current.copy(
                    alpha = ContentAlpha.disabled
                ),)

        },
        selected = isSelected,
        onClick = {
            navHostController.navigate(screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)

                launchSingleTop = true
            }
        },
        unselectedContentColor = TextWhite.copy(alpha = ContentAlpha.disabled),
        selectedContentColor = TextWhite,


        )
}
