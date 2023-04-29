import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.QuestionDisplay
import com.example.learnproject_compose.model.QuizModel

@Composable
fun QuizPage(navController: NavController) {

    QuestionDisplay(
        QuizModel(
        1, "Das ist erste Quiz Frage", "Das ist Richtige Antwort",
        listOf(
            "Das ist Falsche Antwort 1",
            "Das ist Falsche Antwort 2",
            "Das ist Falsche Antwort 3",
            "Das ist Richtige Antwort",
        )
    )
    )

}