package com.example.learnproject_compose.screen.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.Alignment
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
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.DarkerButtonBlue
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.statusBarColor
import com.example.learnproject_compose.util.noRippleClickable
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius


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
    var selectedIndex by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier.padding(all = 12.dp).height(55.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(
            cornerRadius = 34.dp
        ), ballAnimation = Parabolic(tween(600)),
        indentAnimation = Height(tween(600), indentHeight = 12.dp, indentWidth = 60.dp),
        barColor = statusBarColor,
        ballColor = TextWhite
    ) {

        screen.forEachIndexed { index, bottomBarScreen ->
            Box(modifier = Modifier
                .fillMaxSize()
                .noRippleClickable {
                    selectedIndex = index

                        navHostController.navigate(bottomBarScreen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id)
                            launchSingleTop = true
                        } },
                contentAlignment = Alignment.Center

            ) {

                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = bottomBarScreen.icon),
                    contentDescription = null,
                    tint = if (selectedIndex == index) TextWhite else TextWhite.copy(
                        alpha = .2f))

//                AddItem(
//                    screen = bottomBarScreen,
//                    currentDestination = currentDestination,
//                    navHostController = navHostController
//                )

            }
        }


//        screen.forEachIndexed { index, bottomBarScreen ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .noRippleClickable { selectedIndex = index },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    modifier = Modifier.size(25.dp),
//                    painter = painterResource(id = bottomBarScreen.icon),
//                    contentDescription = null,
//                    tint = if (selectedIndex == index) LocalContentColor.current else LocalContentColor.current.copy(
//                        alpha = ContentAlpha.disabled))
//            }
//        }

    }

//    BottomNavigation(
//        modifier = Modifier.clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)),
//        backgroundColor = statusBarColor,
//    ) {
//        screen.forEach { screen ->
//            AddItem(
//                screen = screen,
//                currentDestination = currentDestination,
//                navHostController = navHostController
//            )
//        }
//
//
//    }


}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController,
) {
    val isSelected: Boolean =
        currentDestination?.hierarchy?.any { it.route == screen.route } == true



    BottomNavigationItem(

        label = { Text(text = screen.title) },
        alwaysShowLabel = false,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
                tint = if (isSelected) LocalContentColor.current else LocalContentColor.current.copy(
                    alpha = ContentAlpha.disabled
                ),
            )

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


