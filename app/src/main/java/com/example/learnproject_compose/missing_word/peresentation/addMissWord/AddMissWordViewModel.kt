package com.example.learnproject_compose.missing_word.peresentation.addMissWord

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.example.learnproject_compose.missing_word.peresentation.MissWordCatUiState
import com.example.learnproject_compose.missing_word.peresentation.MissWordUiState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMissWordViewModel @Inject constructor(private val repo: MissingWordRepo) : ViewModel() {
    private val hasUser = repo.hasUser() != null


    val missWordUiState = mutableStateOf(MissWordUiState())


    var addMissWordUiState by mutableStateOf(AddMissUiState())

    var quizIndex = mutableStateOf(0)


    var score = mutableIntStateOf(0)


    fun onQuestionChange(question: String) {
        addMissWordUiState = addMissWordUiState.copy(question = question)
    }

    fun onCorAnswerChange(corAnswer: String) {
        addMissWordUiState = addMissWordUiState.copy(corAnswer = corAnswer)
    }

    fun onAnswer1Change(answer1: String) {
        addMissWordUiState = addMissWordUiState.copy(answer1 = answer1)
    }

    fun onAnswer2Change(answer2: String) {
        addMissWordUiState = addMissWordUiState.copy(answer2 = answer2)
    }

    fun onAnswer3Change(answer3: String) {
        addMissWordUiState = addMissWordUiState.copy(answer3 = answer3)
    }

    fun onSpacerChange(spacer: String) {
        addMissWordUiState =
            addMissWordUiState.copy(question = addMissWordUiState.question + spacer)
    }

//    init {
//        getMissWordFromFireStore(level = "A1", route = "aW8T4jsI9WiSC1fVeIe6")
//    }


    fun getMissWordFromFireStore(route: String?) = viewModelScope.launch {
        if (route?.isNotEmpty() == true) {

            val missWordRef = Firebase.firestore
                .collection("missingWord")?.document(route)?.collection(route)
            repo.getMissingWordFromFireStore(missWordRef = missWordRef).collect {
                missWordUiState.value = missWordUiState.value.copy(missingWordList = it)

            }
        }


    }


    fun addMissToFireStore(documen: String, collection: String) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")
            .document(documen).collection(collection)
        if (hasUser) {
            repo.addMissingWordToFireStore(
                question = addMissWordUiState.question,
                corAnswer = addMissWordUiState.corAnswer,
                answer1 = addMissWordUiState.answer1,
                answer2 = addMissWordUiState.answer2,
                answer3 = addMissWordUiState.answer3,
                missWordRef = missWordRef,
            ) {
                addMissWordUiState = addMissWordUiState.copy(addStatus = it)

            }
        }
    }

    fun setQuizEditField(missWord: MissingWordModel) {
        addMissWordUiState = missWord.question?.let {
            missWord.corAnswer?.let { it1 ->
                missWord.answer1?.let { it2 ->
                    missWord.answer2?.let { it3 ->
                        missWord.answer3?.let { it4 ->
                            addMissWordUiState.copy(
                                question = it,
                                corAnswer = it1,
                                answer1 = it2,
                                answer2 = it3,
                                answer3 = it4,
                            )
                        }
                    }
                }
            }
        }!!
    }


    fun resetMissState() {
        addMissWordUiState = AddMissUiState()
    }


}


data class AddMissUiState(
    val documenId: String = "",
    val question: String = "",
    val corAnswer: String = "",
    val answer1: String = "",
    val answer2: String = "",
    val answer3: String = "",
    val addStatus: Boolean = false,
    val selectedMissWordCat: MissWordCat? = null,
    val updateMissWordCatStatus: Boolean = false,


    )