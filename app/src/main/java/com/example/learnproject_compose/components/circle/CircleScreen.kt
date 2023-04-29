package com.example.learnproject_compose.components.circle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.Circle
import com.example.learnproject_compose.ui.theme.BlueViolet3

@Composable
fun CirclerScreen(indicatorValue: Int,maxIndicator:Int, ) {


    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Circle(
            indicatorValue = indicatorValue,
            maxIndicatorValue = maxIndicator,
            bigTextSuffix = "300", smallText = "Ergibnis",
            forgroundIndicatorColor = BlueViolet3
        )



    }
}



@Preview(showBackground = true)
@Composable
fun CirclerPreview() {
    CirclerScreen(indicatorValue = 100, maxIndicator = 100)
}