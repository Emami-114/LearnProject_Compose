package com.example.learnproject_compose

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.learnproject_compose.local.Databases
import com.example.learnproject_compose.model.QuizModel
import javax.inject.Inject

class QuizRepository @Inject constructor(private val db: Databases) {

    private val _quizFilterList: MutableState<List<QuizModel>> = mutableStateOf(listOf())
    val quizFilterList: State<List<QuizModel>> = _quizFilterList


    private val _quizAll: MutableState<List<QuizModel>> = mutableStateOf(listOf())
    val quizAll: State<List<QuizModel>> = _quizAll


     fun getLimit() = db.quizDao.getAll()


    suspend fun getAllSucsses() {
        try {
            _quizFilterList.value = db.quizDao.getAllSucsses(0)

        } catch (e: Exception) {
            Log.d("MYTAG", "Repository Get All Sucsses ${e.message}")
        }
    }


    suspend fun inserAllToDatabase() {
        try {
            db.quizDao.insert(loadQuiz())

        } catch (e: Exception) {
            Log.d("MYTAG", "InsertAll to Database: ${e.message}")
        }
    }

    suspend fun getAllQuiz() {
        try {
//            _quizAll.value = db.quizDao.getAll()

        } catch (e: Exception) {
            Log.d("MYTAG", "Repository Get All Sucsses ${e.message}")
        }
    }


    suspend fun update(isSucsses: Boolean, id: Int) = db.quizDao.update(id, isSucsses)


    fun loadQuiz(): List<QuizModel> {
        return listOf<QuizModel>(
            QuizModel(
                1,
                "In Deutschland sind Jugendliche ab 14 Jahren strafmündig. Das bedeutet: Jugendliche, die 14 Jahre und älter sind und gegen Strafgesetze verstoßen, …",
                answerCorrect = "werden bestraft",
                "werden wie Erwachsene behandelt",
                "teilen die Strafe mit ihren Eltern", "werden nicht bestraft"


            ),
            QuizModel(
                2,
                "Wie waren die Besatzungszonen Deutschlands nach 1945 verteilt?",
                answerCorrect = "1=Großbritannien, 2=Sowjetunion, 3=USA, 4=Frankreich",
                "1=Großbritannien, 2=Sowjetunion, 3=Frankreich, 4=USA",
                "1=Großbritannien, 2=USA, 3=Sowjetunion, 4=Frankreich",
                "1=Großbritannien, 2=Sowjetunion, 3=USA, 4=Frankreich"


            ),
            QuizModel(
                3,
                "Wenn man in Deutschland ein Kind schlägt, …",
                answerCorrect = "kann man dafür bestraft werden.",
                "geht das niemanden etwas an.",
                "kann man dafür nicht bestraft werden.",
                "geht das nur die Familie etwas an."


            ),
            QuizModel(
                4,
                "In Deutschland dürfen Menschen offen etwas gegen die Regierung sagen, weil …",
                "hier Meinungsfreiheit gilt.",
                "hier Religionsfreiheit gilt.",
                "die Menschen Steuern zahlen.",
                "die Menschen das Wahlrecht haben."
            ),
            QuizModel(
                5,
                question = "In Deutschland können Eltern bis zum 14. Lebensjahr ihres Kindes entscheiden, ob es in der Schule am …",
                answerCorrect = "Religionsunterricht teilnimmt.",
                answer = "Geschichtsunterricht teilnimmt.",
                answer2 = "Politikunterricht teilnimmt.",
                answer3 = "Sprachunterricht teilnimmt."
            ), QuizModel(
                5,
                question = "Deutschland ist ein Rechtsstaat. Was ist damit gemeint?",
                answerCorrect = "Alle Einwohner / Einwohnerinnen und der Staat müssen sich an die Gesetze halten.",
                answer = "Der Staat muss sich nicht an die Gesetze halten.",
                answer2 = "Nur Deutsche müssen die Gesetze befolgen.",
                answer3 = "Die Gerichte machen die Gesetze."
            ),
            QuizModel(
                6,
                question = "Welches Recht gehört zu den Grundrechten in Deutschland?",
                answerCorrect = "Meinungsfreiheit",
                answer = "Waffenbesitz",
                answer2 = "Faustrecht",
                answer3 = "Selbstjustiz"
            ),
            QuizModel(
                7,
                question = "Wahlen in Deutschland sind frei. Was bedeutet das?",
                answerCorrect = "Der Wähler darf bei der Wahl weder beeinflusst noch zu einer bestimmten Stimmabgabe gezwungen werden und keine Nachteile durch die Wahl haben.",
                answer = "Man darf Geld annehmen, wenn man dafür einen bestimmten Kandidaten / eine bestimmte Kandidatin wählt.",
                answer2 = "Nur Personen, die noch nie im Gefängnis waren, dürfen wählen.",
                answer3 = "Alle wahlberechtigten Personen müssen wählen."
            ),
            QuizModel(
                8,
                question = "Wie heißt die deutsche Verfassung?",
                answerCorrect = "Grundgesetz",
                answer = "Volksgesetz",
                answer2 = "Bundesgesetz",
                answer3 = "Deutsches Gesetz"
            ),
            QuizModel(
                9,
                question = "Welches Recht gehört zu den Grundrechten, die nach der deutschen Verfassung garantiert werden? Das Recht auf …",
                answerCorrect = "Glaubens- und Gewissensfreiheit",
                answer = "Unterhaltung",
                answer2 = "Arbeit",
                answer3 = "Wohnung"
            ),
            QuizModel(
                10,
                question = "Was steht nicht im Grundgesetz von Deutschland?",
                answerCorrect = "Alle sollen gleich viel Geld haben.",
                answer = "Die Würde des Menschen ist unantastbar.",
                answer2 = "Jeder Mensch darf seine Meinung sagen.",
                answer3 = "Alle sind vor dem Gesetz gleich."
            ),
            QuizModel(
                11,
                question = "Welches Grundrecht gilt in Deutschland nur für Ausländer / Ausländerinnen? Das Grundrecht auf …",
                answerCorrect = "Asyl",
                answer = "Schutz der Familie",
                answer2 = "Menschenwürde",
                answer3 = "Meinungsfreiheit"
            )
        )
    }


}