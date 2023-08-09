package com.example.learnproject_compose.components.splashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.EMobiledata
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(700)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(700)
        startAnimation = false
        delay(500)
        startAnimation = true
        delay(700)
        navController.popBackStack()
        navController.navigate(DetailsScreen.HOME2.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha:Float) {
    Box(
        modifier = Modifier
            .background(largeRadialGradient)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .size(160.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.quizapp2),
            contentDescription = null,
        )

    }
}