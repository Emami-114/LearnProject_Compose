package com.example.learnproject_compose.components.circle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.components.Circle
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass

@Composable
fun CirclerScreen(
    totalIndicatorValue: Int,
    correctIndicatorValue: Int,
    failureIndicatorValue: Int,
    maxIndicator: Int,

    ) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val windows = rememberWindowSizeClass()

        Circle(
            canvasSize = if (windows.height.size > 980 ||
                AppTheme.orientation == Orientation.Landscope &&
                windows.height.size > 600) 500.dp else 300.dp,
            totalIndicatorValue = totalIndicatorValue,
            correctIndicatorValue = correctIndicatorValue,
            failureIndicatorValue = failureIndicatorValue,
            maxIndicatorValue = maxIndicator,
            bigTextSuffix = "310", smallText = "Ergibnis",
            forgroundIndicatorColor = BlueViolet3.copy(alpha = .8f),
            forgroundIndicatorColor2 = GreenColor.copy(alpha = .9f),
            forgroundIndicatorColor3 = Color.Red.copy(alpha = .5f),
            bigTextColor = TextWhite,
            smallTextColor = TextWhite.copy(alpha = .8f),
        )


    }
}


//@Preview(showBackground = true)
//@Composable
//fun CirclerPreview() {
//    CirclerScreen(indicatorValue = 100, maxIndicator = 100)
//}