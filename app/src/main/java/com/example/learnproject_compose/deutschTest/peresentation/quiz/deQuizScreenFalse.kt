import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.deutschTest.peresentation.DeQuizViewModel
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint(
    "StateFlowValueCalledInComposition", "UnrememberedMutableState",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun DeQuizPageFalse(navController: NavController) {

    val viewModel2: DeQuizViewModel = hiltViewModel()

//    val quizDeQuizList = viewModel2.deQuizFromDatabaseIsCorOrNot.deQuizList.data
    val deQuizList = viewModel2.deQuizAllFromDatabase.filter { it.isCorOrNot == false }


    val currentquestion = try {
        deQuizList?.get(viewModel2.questionIndex.value % deQuizList.size)

    } catch (e: Exception) {
        null
    }


    val maxQuiz by
    mutableIntStateOf(deQuizList?.count() ?: 0)

//    val currentquestion = viewModel2.qu.value[viewModel2.questionIndex.value]

    val answerList = listOf(
        currentquestion?.answer2,
        currentquestion?.answer3,
        currentquestion?.answer1,
        currentquestion?.corAnswer
    ).shuffled()

    val deMarket = mutableStateOf(currentquestion?.isFavors ?: false)


    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepBlue),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(8f)) {

                TopBarSelb(title = "Zurück") {
                    navController.popBackStack()
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                IconButton(onClick = {
                    deMarket.value = !deMarket.value
                    viewModel2.updateDeQuizFavorite(currentquestion?.id, deMarket.value)
                }) {
                    Icon(
                        imageVector = if (deMarket.value) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                        contentDescription = null, tint = TextWhite, modifier = Modifier.size(
                            AppTheme.dimens.mediumLarge
                        )
                    )


                }
            }
        }
    }, contentWindowInsets = WindowInsets(AppTheme.dimens.small)) {

//        when (viewModel2.deQuizFromDatabaseIsCorOrNot.deQuizList) {
//            is Resources2.Loading -> {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(largeRadialGradient)
//                        .padding(it),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    CircularProgressIndicator(color = TextWhite)
//                }
//            }
//
//            is Resources2.Success -> {
        if (currentquestion != null) {
            Column(
            ) {
                var quizMarket = remember { mutableStateOf(false) }

                QuestionDisplay(
                    viewModel = viewModel2,
                    quizModel = currentquestion,
                    maxQuiz = maxQuiz,
                    answerList = answerList,
                    viewModel2.questionIndex,
                    paddingValues = it
                ) {

                    if (viewModel2.questionIndex.value > (deQuizList
                            ?.count() ?: 0)
                    ) {
                        navController.popBackStack()
                    }

                }
            }
        }

//            }

//            is Resources2.Error -> {}

//        }


        if (deQuizList?.isEmpty() == true) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(largeRadialGradient),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Keine Daten gefunden :(", color = TextWhite)

            }
        }
    }
}