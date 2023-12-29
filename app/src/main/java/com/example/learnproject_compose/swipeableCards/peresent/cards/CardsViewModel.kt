package com.example.learnproject_compose.swipeableCards.peresent.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.example.learnproject_compose.missing_word.peresentation.MissWordCatUiState
import com.example.learnproject_compose.missing_word.peresentation.MissWordUiState
import com.example.learnproject_compose.swipeableCards.local.model.CardModule
import com.example.learnproject_compose.swipeableCards.local.repository.CardRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(private val repo: CardRepository) : ViewModel() {
    private val hasUser = repo.hasUser() != null


    val cardUiState =
        mutableStateOf<Resources2<List<CardModule>>>(Resources2.Loading())


    var addCardsUiState by mutableStateOf(AddCardUiState())

    var quizIndex = mutableIntStateOf(0)



//    private val missWordRef = Firebase.firestore.collection("missingWord")
//        .document("").collection("")


    fun onQuestionChange(question: String) {
        addCardsUiState = addCardsUiState.copy(question = question)
    }

    fun onCorAnswerChange(corAnswer: String) {
        addCardsUiState = addCardsUiState.copy(corAnswer = corAnswer)
    }





    fun getCardsFromFireStore(route: String?) = viewModelScope.launch {
        if (route?.isNotEmpty() == true) {

            val cardRef = Firebase.firestore
                .collection("cardSet").document(route).collection(route)
            repo.getCardsFromFireStore(cardsRef  = cardRef).collect {
                cardUiState.value = it

            }
        }


    }


    fun addCardsToFireStore(documen: String, collection: String) = viewModelScope.launch {
        val cardRef = Firebase.firestore.collection("cardSet")
            .document(documen).collection(collection)
        if (hasUser) {
            repo.addCardToFireStore(
                question = addCardsUiState.question,
                corAnswer = addCardsUiState.corAnswer,
                cardsRef = cardRef,
            ) {
                addCardsUiState = addCardsUiState.copy(addStatus = it)
            }
        }
    }
    fun resetMissState() {
        addCardsUiState = AddCardUiState()
    }
}
data class AddCardUiState(
    val documenId: String = "",
    val question: String = "",
    val corAnswer: String = "",
    val addStatus: Boolean = false,
    val selectedMissWordCat: MissWordCat? = null,
    val updateMissWordCatStatus: Boolean = false,


    )