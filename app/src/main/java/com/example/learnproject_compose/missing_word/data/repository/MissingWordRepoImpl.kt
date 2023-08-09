package com.example.learnproject_compose.missing_word.data.repository

import android.util.Log
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MissingWordRepoImpl @Inject constructor() :
    MissingWordRepo {


    // TODO: MissWordCat Funktion

    override suspend fun getMissingWordCatFromFireStore(
        level: String?,
        missWordRef: CollectionReference
    ): Flow<Resources2<List<MissWordCat>>> =
        callbackFlow {
            var snapshotStateListener: ListenerRegistration? = null
            if (level == "Alle") {
                try {
                    snapshotStateListener = missWordRef

//                      .whereEqualTo("level", level.orEmpty())


//                    .orderBy("id")
                        .addSnapshotListener { snapshot, error ->
                            val response = if (snapshot != null) {

                                val data = snapshot.toObjects(MissWordCat::class.java)
                                Resources2.Success(data = data)
                            } else {
                                Resources2.Error(error)
                            }
                            trySend(response)
                        }
                } catch (e: Exception) {
                    trySend(Resources2.Error(e))
                    e.printStackTrace()
                }
            } else {
                try {
                    snapshotStateListener = missWordRef

                        .whereEqualTo("level", level)
                        .whereEqualTo("active", true)


//                    .orderBy("id")
                        .addSnapshotListener { snapshot, error ->
                            val response = if (snapshot != null) {

                                val data = snapshot.toObjects(MissWordCat::class.java)
                                Resources2.Success(data = data)
                            } else {
                                Resources2.Error(error)
                            }
                            trySend(response)
                        }
                } catch (e: Exception) {
                    trySend(Resources2.Error(e))
                    e.printStackTrace()
                }
            }

            awaitClose {
                snapshotStateListener?.remove()
            }
        }


    override fun getMissingWordCatSingle(
        documentId: String,
        missWordRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (MissWordCat?) -> Unit
    ) {
        missWordRef.document(documentId).get().addOnSuccessListener {
            onSuccess.invoke(it?.toObject(MissWordCat::class.java))
        }
            .addOnFailureListener {
                onError.invoke(it.cause)
            }

    }

    override suspend fun updateMissWordCategorie(
        title: String,
        level: String,
        description: String,
        color: Int,
        documentId: String,
        isActive: Boolean,
        new: Boolean,
        missWordRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {

        val updateData = hashMapOf<String, Any>(
            "title" to title,
            "level" to level,
            "description" to description,
            "color" to color,
            "documentId" to documentId,
            "active" to isActive,
            "new" to new,
        )

        missWordRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }

    }


    override suspend fun updateMissWordCategorieNew(

        documentId: String,
        new: Boolean,
        missWordRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {

        val updateData = hashMapOf<String, Any>(
            "new" to new,
        )

        missWordRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }

    }

    override suspend fun updateMissWordCategorieItemCount(

        documentId: String,
        itemCount: Int,
        missWordRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {

        val updateData = hashMapOf<String, Any>(
            "itemCount" to itemCount,
        )

        missWordRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }

    }


    override fun deleteMissWordCat(
        documentId: String,
        missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    ) {
        missWordRef.document(documentId).delete()
            .addOnCompleteListener { onComplete.invoke(it.isSuccessful) }
    }


    override suspend fun addMissingWordCatToFireStore(
        userId: String,
        title: String,
        level: String,
        description: String,
        active: Boolean,
        timestamp: Timestamp,
        userName: String,
        color: Int,
        missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit,
        onNavigate: (id: String) -> Unit
    ) {
        val documentId = missWordRef.document().id

        val missWordCat = MissWordCat(
            userId = userId,
            title = title,
            description = description,
            timestamp = timestamp,
            documentId = documentId,
            color = color,
            level = level,
            active = active,
            new = true,
            userName = userName,
            itemCount = 0,
        )



        missWordRef.document(documentId)
            .set(missWordCat)
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
                onNavigate.invoke(documentId)
            }
//        missWordRef.document(documentId).collection(documentId).document()
//            .set(missWordCat)
//            .addOnCompleteListener {
//                onComplete.invoke(it.isSuccessful)
//            }

    }

    override suspend fun getMissingWordFromFireStore(
        missWordRef: CollectionReference?
    ): Flow<Resources2<List<MissingWordModel>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null
        try {
            if (missWordRef != null) {
                snapshotStateListener = missWordRef

                    .addSnapshotListener { snapshot, error ->
                        val response = if (snapshot != null) {

                            val data = snapshot.toObjects(MissingWordModel::class.java)
                            Resources2.Success(data = data)
                        } else {
                            Resources2.Error(error)
                        }
                        trySend(response)
                    }
            }
        } catch (e: Exception) {
            trySend(Resources2.Error(e))
            e.printStackTrace()
        }

        awaitClose {
            snapshotStateListener?.remove()
        }
    }


    // TODO: MissWordList Funktion

    override suspend fun addMissingWordToFireStore(
        question: String,
        corAnswer: String,
        answer1: String,
        answer2: String,
        answer3: String?,
        missWordRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    ) {

        val documentId = missWordRef.document().id

        val missWord = MissingWordModel(
            documenId = documentId,
            question = question,
            corAnswer = corAnswer,
            answer1 = answer1,
            answer2 = answer2,
            answer3 = answer3
        )
        missWordRef.document(documentId)
            .set(missWord)
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }

    }


    override suspend fun getUserFromFireStoreSingle(
        userId: String,
        usersRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (UsersModel?) -> Unit
    ) {
        try {

            usersRef.document(userId).get().addOnSuccessListener {
                onSuccess.invoke(it?.toObject(UsersModel::class.java))
            }
                .addOnFailureListener {
                    onError.invoke(it.cause)

                }
        } catch (e: Exception) {
            Log.d("MYTAG", "Repository: ${e.cause}")
        }


    }


}

data class UsersModel(
    val userId: String? = null,
    val email: String? = null,
    val rolle: String? = null,
    val level: String? = null,
    val name: String? = null
)