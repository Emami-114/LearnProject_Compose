package com.example.learnproject_compose.missing_word.local.repository

import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.data.repository.UsersModel
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow


interface MissingWordRepo {
    fun hasUser() = Firebase.auth.currentUser
// TODO: MissWordCategorie Funltion

    suspend fun getMissingWordCatFromFireStore(
        level: String?,
        missWordRef: CollectionReference
    ): Flow<Resources2<List<MissWordCat>>>


    fun getMissingWordCatSingle(
        documentId: String,
        missWordRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (MissWordCat?) -> Unit
    )

    suspend fun updateMissWordCategorie(
        title: String,
        level: String,
        description: String,
        color: Int,
        documentId: String,
        isActive: Boolean,
        new: Boolean,
        missWordRef: CollectionReference,
        onResult: (Boolean) -> Unit

    )
   suspend fun updateMissWordCategorieNew(
    documentId: String,
    new: Boolean,
    missWordRef: CollectionReference,
    onResult: (Boolean) -> Unit
    )

    suspend fun updateMissWordCategorieItemCount(
        documentId: String,
        itemCount: Int,
        missWordRef: CollectionReference,
        onResult: (Boolean) -> Unit
    )


    fun deleteMissWordCat(
        documentId: String, missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    )


    suspend fun addMissingWordCatToFireStore(
        userId: String,
        title: String,
        level: String,
        description: String,
        isActive: Boolean,
        timestamp: Timestamp,
        userName: String,
        color: Int,
        missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit,
        onNavigate: (id: String) -> Unit

    )

    // TODO: MissWordList Funktion


    suspend fun getMissingWordFromFireStore(
        missWordRef: CollectionReference?
    ): Flow<Resources2<List<MissingWordModel>>>


    suspend fun addMissingWordToFireStore(
        question: String,
        corAnswer: String,
        answer1: String,
        answer2: String,
        answer3: String?,
        missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    )


//    suspend fun getUserFromFireStore(
//        userId: String, usersRef: CollectionReference
//    ): Flow<Resources2<List<UsersModel>>>

    suspend fun getUserFromFireStoreSingle(
        userId: String,
        usersRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (UsersModel?) -> Unit
    )

}