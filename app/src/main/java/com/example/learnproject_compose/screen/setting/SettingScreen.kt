package com.example.learnproject_compose.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.screen.home.largeRadialGradient

@Composable
fun SettingPage(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Setting Screen", fontSize = 60.sp, fontWeight = FontWeight.Bold)

    }

}