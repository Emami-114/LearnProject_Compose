import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.peresentation.DeQuizViewModel
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint(
    "StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter",
    "UnrememberedMutableState"
)
@Composable
fun QuizPage(navController: NavController) {

    val viewModel2: DeQuizViewModel = hiltViewModel()

    val deQuizList = viewModel2.deQuizAllFromDatabase



    val currentquestion = try {
        deQuizList?.get(viewModel2.questionIndex.value % deQuizList.size)

    } catch (e: Exception) {
        null
    } ?: DeQuiz()


    val deMarket = mutableStateOf(
        currentquestion.isFavors ?: false
    )


    val answerList = listOf(
        currentquestion.answer2,
        currentquestion.answer3,
        currentquestion.answer1,
        currentquestion.corAnswer
    ).shuffled()



    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepBlue),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(8f)) {

                TopBarSelb(title = "Allgemeine Fragen") {
                    navController.popBackStack()
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                IconButton(onClick = {
                    deMarket.value = !deMarket.value
                    viewModel2.updateDeQuizFavorite(currentquestion.id, deMarket.value)
                }) {
                    Icon(
                        imageVector = if (deMarket.value) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(AppTheme.dimens.mediumLarge)
                    )
                }
            }
        }
    }, contentWindowInsets = WindowInsets(AppTheme.dimens.small)) {

        Column(

        ) {

            QuestionDisplay(
                viewModel = viewModel2,
                quizModel = currentquestion,
                maxQuiz = deQuizList.count(),
                answerList = answerList,
                viewModel2.questionIndex,
                paddingValues = it
            ) {

                if (viewModel2.questionIndex.value > ((deQuizList?.size?.minus(1) ?: 0))
                ) {
                    navController.popBackStack()
                }
            }
        }
    }


}