package com.example.learnproject_compose.swipeableCards.peresent.cardsSet

import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.google.firebase.Timestamp

data class CardsSetUiState(
    val userId: String = "",
    val title: String = "",
    val level: String = "",
    val active: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
    val documentId: String = "",
    val new: Boolean = true,
    val color: Int = 0,
    val userName: String = "",
    val addStatus: Boolean = false,
    val selectedMissWordCat: CardsModuleSet? = null,
    val updateMissWordCatStatus: Boolean = false,
    val updateMissWordCatStatusNew: Boolean = false,
    val deleteStatus: Boolean = false

)
