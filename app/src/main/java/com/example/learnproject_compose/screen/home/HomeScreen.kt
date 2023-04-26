package com.example.learnproject_compose.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.bottomBar.SetupNavGraphBotton
import com.example.learnproject_compose.screen.home.features.FeaturesItem
import com.example.learnproject_compose.screen.home.features.FeaturesSection
import com.example.learnproject_compose.screen.home.features.features
import com.example.learnproject_compose.screen.home.slider.SlideUi
import com.example.learnproject_compose.screen.navigation.Screen
import com.example.learnproject_compose.screen.navigation.SetupNavGraph
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.LightRed

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Greeting()
        SlideUi()
        Text(text = "hallo", fontSize = 20.sp,
            modifier = Modifier.clickable { navController.navigate(BottomBarScreen.Deutsc.route) })
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {
                    navController.navigate(BottomBarScreen.Deutsc.route)
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[2]) {}

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[1]) {}

            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {}

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

            }

        }


//        FeaturesSection()

    }
}


@Composable
fun Greeting(
    greetingText: String = "Welcome Back, John!",
    desc: String = "Great job, keep going to improve your skills"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "$greetingText", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = desc, fontSize = 16.sp)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}