package com.example.learnproject_compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.dictionary.presentation.wordData.WordDataViewModel
import com.example.learnproject_compose.dictionary.remote.DictionaryApiService
import com.example.learnproject_compose.screen.bottomBar.BottomBar
import com.example.learnproject_compose.screen.bottomBar.SetupNavGraphBotton
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.backgroundWhite
import com.example.learnproject_compose.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnProject_ComposeTheme {
                navController = rememberNavController()

                val viewModel2: WordDataViewModel = hiltViewModel()

                Log.d("MYTAG", "Mein Activity2: " + viewModel.quizList.value.toString())
                Log.d("MYTAG", "Mein Activity3: " + viewModel2.wordQuery.value)


//                SetupNavGraph(navHostController = navController)
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = { BottomBar(navHostController = navController) },
                ){
                    Column {
                        SetupNavGraphBotton(navHostController = navController)
                    }
                }

            }
        }
    }
}




