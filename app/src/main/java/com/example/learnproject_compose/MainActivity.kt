package com.example.learnproject_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.screen.bottomBar.BottomBar
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.bottomBar.SetupNavGraphBotton
import com.example.learnproject_compose.screen.home.HomeScreen
import com.example.learnproject_compose.screen.home.slider.SlideUi
import com.example.learnproject_compose.screen.navigation.SetupNavGraph
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnProject_ComposeTheme() {
                navController = rememberNavController()
//                SetupNavGraph(navHostController = navController)
                // A surface container using the 'background' color from the theme

                Scaffold(bottomBar = {
                    BottomBar(navHostController = navController)
                }) {
                    SetupNavGraphBotton(navHostController = navController)

                }

            }
        }
    }
}




