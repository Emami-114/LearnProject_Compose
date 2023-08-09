package com.example.learnproject_compose.components.loadingAnimation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation() {
    val circle = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
    )

    circle.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    }, repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circle.map { it.value }
    val distance = with(LocalDensity.current) { 20.dp.toPx() }
    val lastCircle = circleValues.size - 1
    Column(
        modifier = Modifier.fillMaxSize().background(largeRadialGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    Row(modifier = Modifier) {
        circleValues.forEachIndexed { index, fl ->
            Box(modifier = Modifier
                .size(AppTheme.dimens.mediumLarge)
                .graphicsLayer { translationY = -fl * distance }
                .border(
                    width = 1.3.dp,
                    color = LightGreen1,
                    shape = RoundedCornerShape(AppTheme.dimens.smallMedium)
                )

            )
            if (index != lastCircle) {
                Spacer(modifier = Modifier.width(AppTheme.dimens.mediumLarge))
            }
        }
    }
    }


}