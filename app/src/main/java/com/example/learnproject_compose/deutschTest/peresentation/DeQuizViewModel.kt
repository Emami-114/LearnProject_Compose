package com.example.learnproject_compose.deutschTest.peresentation

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.learnproject_compose.QuizRepository
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.deutschTest.domain.repository.DeQuizRepository
import com.example.learnproject_compose.local.Databases
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeQuizViewModel @Inject constructor(
    private val repo: DeQuizRepository,
    private val db: Databases,
    private val context:Application
) : ViewModel() {

    var deQuizFromDatabase by mutableStateOf(HomeUiState())
        private set

    var deQuizFromDatabaseRange by mutableStateOf(HomeUiState())
        private set


    var deQuizFromDatabaseRange2 = mutableStateListOf<DeQuiz>()
        private set

    var deQuizAllFromDatabase = mutableStateListOf<DeQuiz>()
        private set


    var deQuizFromDatabaseIsCorOrNot by mutableStateOf(HomeUiState())
        private set

    var deQuizFromDatabaseIsFavorite by mutableStateOf(HomeUiState())
        private set

    var totalCount by mutableStateOf<Int>(0)
        private set
    var correctTotalCount by mutableStateOf<Int>(0)
        private set


    val questionIndex = mutableIntStateOf(0)

    var quizTestResult = mutableIntStateOf(0)

    var selectedBund =
        mutableStateOf(
            BundPreferences.getSelectedBund(context) ?: ""
        )



    init {
        getDeQuizFromFireStore()
        getAllFavorite(favorite = true)
        getDeQuizAllFromDatabase()

      bundQuiz(selectedBund.value)

    }

    fun bundQuiz(bund: String) {
        when (bund) {
            "Baden-Württemberg" -> getDeQuizFromDatabaseRange(301, 310)
            "Bayern" -> getDeQuizFromDatabaseRange(311, 320)
            "Berlin" -> getDeQuizFromDatabaseRange(321, 330)
            "Brandenburg" -> getDeQuizFromDatabaseRange(331, 340)
            "Bremen" -> getDeQuizFromDatabaseRange(341, 350)
            "Hamburg" -> getDeQuizFromDatabaseRange(351, 360)
            "Hessen" -> getDeQuizFromDatabaseRange(361, 370)
            "Mecklenburg-Vorpommern" -> getDeQuizFromDatabaseRange(371, 380)
            "Niedersachsen" -> getDeQuizFromDatabaseRange(381, 390)
            "Nordrhein-Westfalen" -> getDeQuizFromDatabaseRange(391, 400)
            "Rheinland-Pfalz" -> getDeQuizFromDatabaseRange(401, 410)
            "Saarland" -> getDeQuizFromDatabaseRange(411, 420)
            "Sachsen" -> getDeQuizFromDatabaseRange(421, 430)
            "Sachsen-Anhalt" -> getDeQuizFromDatabaseRange(431, 440)
            "Schleswig-Holstein" -> getDeQuizFromDatabaseRange(441, 450)
            "Thüringen" -> getDeQuizFromDatabaseRange(451, 460)
        }
    }

    private fun getAllFavorite(favorite: Boolean) = viewModelScope.launch {
        repo.getAllFavorite(favorite).collect {
            deQuizFromDatabaseIsFavorite = deQuizFromDatabaseIsFavorite.copy(deQuizList = it)
        }
    }

    fun updateDeQuizFavorite(id: String?, favorite: Boolean) = viewModelScope.launch {
        db.deQuizDao.updateDeQuizFavorite(id, favorite)
    }

    fun updateDeQuiz(id: String?, isCorOrNot: Boolean) = viewModelScope.launch {
        db.deQuizDao.updateDeQuiz(id, isCorOrNot)
    }

    private fun getDeQuizFromDatabaseRange(startId: Int, endId: Int) = viewModelScope.launch {
        repo.getDeQuizRange(startId, endId).collect {


            deQuizAllFromDatabase.addAll(it.data ?: listOf())

        }
    }

     fun getDeQuizAllFromDatabase() = viewModelScope.launch {
        repo.getDeQuizRange(1, 300).collect {
            deQuizAllFromDatabase.addAll(it.data ?: listOf())

        }
    }


    private fun getDeQuizFromFireStore() = viewModelScope.launch {
        repo.getQuizFormFireStore().collect {
            it.data?.let { it1 -> db.deQuizDao.insertDeQuiz(it1) }
        }
    }
}


data class HomeUiState(
    var deQuizList: Resources2<List<DeQuiz>> = Resources2.Loading(),
)




