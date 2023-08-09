package com.example.learnproject_compose.screen.bottomBar

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.navigation.BarrScreen
import com.example.learnproject_compose.ui.theme.DeepBlue2
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.statusBarColor
import com.example.learnproject_compose.util.noRippleClickable
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius


@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomBar(navHostController: NavHostController) {

    val screen = listOf(
        BarrScreen.Home,
        BarrScreen.Search,
        BarrScreen.Dictionary,
        BarrScreen.Setting
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var selectedIndex by remember { mutableIntStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(all = 12.dp)
            .height(55.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(
            cornerRadius = 34.dp
        ), ballAnimation = Parabolic(tween(600)),
        indentAnimation = Height(tween(600), indentHeight = 12.dp, indentWidth = 60.dp),
        barColor = DeepBlue2,
        ballColor = TextWhite
    ) {




        screen.forEachIndexed { index, bottomBarScreen ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        selectedIndex = index

                        navHostController.navigate(bottomBarScreen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                contentAlignment = Alignment.Center

            ) {

                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = bottomBarScreen.icon,
                    contentDescription = null,
                    tint = if (selectedIndex == index) TextWhite else TextWhite.copy(
                        alpha = .2f
                    ))

            }
        }








}

//@Composable
//fun BottomBarr(navController: NavHostController) {
//    val screens = listOf(
//        BottomBarrScreen.Home,
//        BottomBarrScreen.Profile,
//        BottomBarrScreen.Setting,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
//    if (bottomBarDestination) {
//        BottomNavigation {
//            screens.forEach { screen ->
//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
//    }
//}

@Composable
fun RowScope.AddItem(
    screen: BarrScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}}

//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarrScreen,
//    currentDestination: NavDestination?,
//    navHostController: NavHostController,
//) {
//    val isSelected: Boolean =
//        currentDestination?.hierarchy?.any { it.route == screen.route } == true
//
//
//
//    BottomNavigationItem(
//
//        label = { Text(text = screen.title) },
//        alwaysShowLabel = false,
//        icon = {
//            Icon(
//                imageVector = screen.icon,
//                contentDescription = screen.title,
//                tint = if (isSelected) LocalContentColor.current else LocalContentColor.current.copy(
//                    alpha = ContentAlpha.disabled
//                ),
//            )
//
//        },
//        selected = isSelected,
//        onClick = {
//            navHostController.navigate(screen.route) {
//                popUpTo(navHostController.graph.findStartDestination().id)
//
//                launchSingleTop = true
//            }
//        },
//        unselectedContentColor = TextWhite.copy(alpha = ContentAlpha.disabled),
//        selectedContentColor = TextWhite,
//
//
//        )
//}


