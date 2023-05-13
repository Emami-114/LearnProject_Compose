import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.model.QuizModel
import com.example.learnproject_compose.viewModel.MainViewModel
import kotlinx.coroutines.delay

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun QuizPage(navController: NavController) {

    val viewModel: MainViewModel = hiltViewModel()

    val questionIndex = remember { mutableStateOf(0) }

    val questionList = viewModel.items


    val currentquestion = try {

        questionList.value.get(viewModel.questionIndex.value)

    } catch (e: Exception) {
        null
    }

    val quizModel = viewModel.quizList.value[viewModel.questionIndex.value]
//    val quizList2 = viewModel.quizListFlow.collectAsState(initial = listOf())


    if (currentquestion != null) {
        QuestionDisplay(viewModel = viewModel, quizModel = currentquestion, viewModel.questionIndex) {

            if (viewModel.questionIndex.value >= viewModel.quizList.value.size-1){
                navController.popBackStack()
            }
        }
    }

}