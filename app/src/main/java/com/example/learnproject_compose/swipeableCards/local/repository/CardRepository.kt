package com.example.learnproject_compose.swipeableCards.local.repository

import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.swipeableCards.local.model.CardModule
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface CardRepository  {
    fun hasUser() = Firebase.auth.currentUser

    suspend fun getCardsSetFromFireStore(
        level: String?,
        missWordRef: CollectionReference
    ): Flow<Resources2<List<CardsModuleSet>>>



    fun getCarsSetSingle(
        documentId: String,
        cardsRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (CardsModuleSet?) -> Unit
    )

    suspend fun updateCardsSet(
        title: String,
        level: String,
        color: Int,
        documentId: String,
        new: Boolean,
        active: Boolean,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    )


    suspend fun updateCardsSetNew(
        documentId: String,
        new: Boolean,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    )

    suspend fun updateCardsSetItemCount(
        documentId: String,
        itemCount: Int,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    )


    fun deleteCardsSet(
        documentId: String,
        cardsRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    )


    suspend fun addCardsSetToFireStore(
        userId: String,
        title: String,
        level: String,
        active: Boolean,
        timestamp: Timestamp,
        userName: String,
        color: Int,
        cardsRef: CollectionReference,
        onComplete: (Boolean) -> Unit,
        onNavigate: (id: String) -> Unit

    )

    // TODO: CardsList Funktion


    suspend fun getCardsFromFireStore(
        cardsRef: CollectionReference?
    ): Flow<Resources2<List<CardModule>>>


    suspend fun addCardToFireStore(
        question: String,
        corAnswer: String,
        cardsRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    )

}