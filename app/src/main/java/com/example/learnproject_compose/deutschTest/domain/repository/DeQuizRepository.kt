package com.example.learnproject_compose.deutschTest.domain.repository

import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow


interface DeQuizRepository {

    fun getQuizFormFireStore(): Flow<Resources2<List<DeQuiz>>>

    suspend fun getDeQuizRandomLimit(limit:Int): Flow<Resources2<List<DeQuiz>>>

    suspend fun getDeQuizRange(startId:Int,endId:Int): Flow<Resources2<List<DeQuiz>>>

    suspend fun getAllCoreOrNot(isCorOrNot: Int): Flow<Resources2<List<DeQuiz>>>

    suspend fun updateDeQuiz(id: String, isCorOrNot: Boolean)


    suspend fun getAllFavorite(favorite: Boolean): Flow<Resources2<List<DeQuiz>>>

    suspend fun updateDeQuizFavorite(id: String, isFavors: Boolean)




    suspend fun addDeQuizToFireStore(
        id: String = "",
        question: String? = null,
        corAnswer: String? = null,
        answer1: String? = null,
        answer2: String? = null,
        answer3: String? = null,
        url: String? = null,
        isCorOrNot: Boolean? = null,
        isFavors: Boolean? = null,
        deQuizRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    ) {
        val documentId = deQuizRef.document().id

        val deQuiz = DeQuiz(
            id = id,
            question = question,
            corAnswer = corAnswer,
            answer1 = answer1,
            answer2 = answer2,
            answer3 = answer3,
            url = url,
            isCorOrNot = isCorOrNot,
            isFavors = isFavors,
        )
        deQuizRef.document(id).set(deQuiz).addOnCompleteListener {
            onComplete.invoke(it.isSuccessful)
        }

    }


}


