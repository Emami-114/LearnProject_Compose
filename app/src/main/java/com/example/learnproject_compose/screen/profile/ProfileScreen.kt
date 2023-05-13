package com.example.learnproject_compose.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnproject_compose.screen.home.largeRadialGradient

@Composable
fun ProfilePage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(largeRadialGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen", fontSize = 60.sp, fontWeight = FontWeight.Bold)
    }

}