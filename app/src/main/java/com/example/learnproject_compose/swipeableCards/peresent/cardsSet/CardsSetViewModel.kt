package com.example.learnproject_compose.swipeableCards.peresent.cardsSet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen.ColorList
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.example.learnproject_compose.swipeableCards.local.repository.CardRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardsSetViewModel @Inject constructor(private val repo: CardRepository) : ViewModel() {

    private val userId = repo.hasUser()?.uid.orEmpty()
    private val userName = repo.hasUser()?.displayName.orEmpty()

    var cardsSetUiState by mutableStateOf<Resources2<List<CardsModuleSet>>>(Resources2.Loading())
        private set

    var addCardsSetUiState by mutableStateOf(CardsSetUiState())
        private set


    fun getCardsSetUserNivua(isUserAdmin: Boolean, userNiva: String) {
        if (isUserAdmin) {
            getCardsSetFromFireStore("Alle")
        } else {
            getCardsSetFromFireStore(userNiva)


        }
    }

    private fun getCardsSetFromFireStore(level: String?) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("cardSet")

        repo.getCardsSetFromFireStore(level, missWordRef).collect {
            cardsSetUiState = it
        }
    }


    fun setEditField(cardsSet: CardsModuleSet) {
        addCardsSetUiState =
            addCardsSetUiState.copy(
                title = cardsSet.title,
                level = cardsSet.level,
                active = cardsSet.active,
                new = cardsSet.new,
                color = cardsSet.color
            )
    }

    fun updateCardsSet(documentId: String, navController: NavController) = viewModelScope.launch {
        val cardsRef = Firebase.firestore.collection("cardSet")

        repo.updateCardsSet(
            title = addCardsSetUiState.title,
            level = addCardsSetUiState.level,
            color = addCardsSetUiState.color,
            documentId = documentId,
            active = addCardsSetUiState.active,
            new = addCardsSetUiState.new,
            cardsRef = cardsRef,
        ) {

            addCardsSetUiState = addCardsSetUiState.copy(updateMissWordCatStatus = it)

            navController.navigateUp()

        }
    }

    fun updateCardsSetNew(documentId: String) = viewModelScope.launch {
        val cardsRef = Firebase.firestore.collection("cardSet")

        repo.updateCardsSetNew(
            documentId = documentId,
            new = false,
            cardsRef = cardsRef,

            ) {

            addCardsSetUiState = addCardsSetUiState.copy(updateMissWordCatStatusNew = it)
        }
    }


    fun updateCardsItemCount(documentId: String, itemCount: Int) = viewModelScope.launch {
        val cardsRef = Firebase.firestore.collection("cardSet")

        repo.updateCardsSetItemCount(
            documentId = documentId,
            itemCount = itemCount,
            cardsRef = cardsRef,
        ) {
            addCardsSetUiState = addCardsSetUiState.copy(updateMissWordCatStatusNew = it)
        }
    }


    fun getCardsSetSingle(documentId: String) {
        val cardsRef = Firebase.firestore.collection("cardSet")

        repo.getCarsSetSingle(documentId = documentId,
            cardsRef = cardsRef,
            onError = {}) {
            addCardsSetUiState = addCardsSetUiState.copy(selectedMissWordCat = it)
            addCardsSetUiState.selectedMissWordCat?.let { it -> setEditField(it) }
        }
    }

    fun deleteMissWordCat(documentId: String) {
        val cardsRef = Firebase.firestore.collection("cardSet")

        repo.deleteCardsSet(documentId, cardsRef = cardsRef) {
            addCardsSetUiState = addCardsSetUiState.copy(deleteStatus = it)
        }
    }


    fun onTitleChange(value: String) {
        addCardsSetUiState = addCardsSetUiState.copy(title = value)
    }

    fun onLevelChange(value: String) {
        addCardsSetUiState = addCardsSetUiState.copy(level = value)
    }

    fun onActiveChange(value: Boolean) {
        addCardsSetUiState = addCardsSetUiState.copy(active = value)
    }

    fun onColorChange(value: Int) {
        addCardsSetUiState = addCardsSetUiState.copy(color = value)
    }

    fun onNewChange(value: Boolean) {
        addCardsSetUiState = addCardsSetUiState.copy(new = value)
    }

    fun addCardsSetToFireStore(navController: NavController) = viewModelScope.launch {
        val cardRef = Firebase.firestore.collection("cardSet")
        repo.addCardsSetToFireStore(
            userId = userId,
            title = addCardsSetUiState.title,
            level = addCardsSetUiState.level,
            active = addCardsSetUiState.active,
            timestamp = Timestamp.now(),
            userName = userName,
            color = addCardsSetUiState.color,
            cardsRef = cardRef,
            onNavigate = {
                navController.navigate(DetailsScreen.AddCardItem.passId(it))
            },
            onComplete = {
                addCardsSetUiState = addCardsSetUiState.copy(addStatus = it)
            }
        )
    }


    fun resetCardsSetState() {
        addCardsSetUiState = CardsSetUiState()
    }

}

