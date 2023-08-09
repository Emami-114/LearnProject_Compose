package com.example.learnproject_compose.swipeableCards.repositoryImp

import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.swipeableCards.local.model.CardModule
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.example.learnproject_compose.swipeableCards.local.repository.CardRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CardRepoImpl @Inject constructor() :
    CardRepository {


    // TODO: MissWordCat Funktion
    override suspend fun getCardsSetFromFireStore(
        level: String?,
        missWordRef: CollectionReference
    ): Flow<Resources2<List<CardsModuleSet>>> = callbackFlow{
        var snapshotStateListener: ListenerRegistration? = null
        if (level == "Alle") {
            try {
                snapshotStateListener = missWordRef

//                      .whereEqualTo("level", level.orEmpty())


//                    .orderBy("id")
                    .addSnapshotListener { snapshot, error ->
                        val response = if (snapshot != null) {

                            val data = snapshot.toObjects(CardsModuleSet::class.java)
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

                            val data = snapshot.toObjects(CardsModuleSet::class.java)
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

    override fun getCarsSetSingle(
        documentId: String,
        cardsRef: CollectionReference,
        onError: (Throwable?) -> Unit,
        onSuccess: (CardsModuleSet?) -> Unit
    ) {
        cardsRef.document(documentId).get().addOnSuccessListener {
            onSuccess.invoke(it?.toObject(CardsModuleSet::class.java))
        }
            .addOnFailureListener {
                onError.invoke(it.cause)
            }
    }

    override suspend fun updateCardsSet(
        title: String,
        level: String,
        color: Int,
        documentId: String,
        new: Boolean,
        active: Boolean,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {
        val updateData = hashMapOf<String, Any>(
            "title" to title,
            "level" to level,
            "color" to color,
            "active" to active,
            "new" to new,
        )

        cardsRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }


    }

    override suspend fun updateCardsSetNew(
        documentId: String,
        new: Boolean,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {
        val updateData = hashMapOf<String, Any>(
            "new" to new,
        )
        cardsRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }

    }

    override suspend fun updateCardsSetItemCount(
        documentId: String,
        itemCount: Int,
        cardsRef: CollectionReference,
        onResult: (Boolean) -> Unit
    ) {
        val updateData = hashMapOf<String, Any>(
            "itemCount" to itemCount,
        )

        cardsRef.document(documentId).update(updateData).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }


    }

    override fun deleteCardsSet(
        documentId: String,
        cardsRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    ) {
        cardsRef.document(documentId).delete()
            .addOnCompleteListener { onComplete.invoke(it.isSuccessful) }
    }


    override suspend fun addCardsSetToFireStore(
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
    ) {
        val documentId = cardsRef.document().id

        val cardsSet = CardsModuleSet(
            userId = userId,
            title = title,
            timestamp = timestamp,
            documentId = documentId,
            color = color,
            level = level,
            active = active,
            new = true,
            userName = userName,
            itemCount = 0,
        )



        cardsRef.document(documentId)
            .set(cardsSet)
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
                onNavigate.invoke(documentId)
            }

    }

    override suspend fun getCardsFromFireStore(cardsRef: CollectionReference?): Flow<Resources2<List<CardModule>>> =
        callbackFlow {
            var snapshotStateListener: ListenerRegistration? = null
            try {
                if (cardsRef != null) {
                    snapshotStateListener = cardsRef

                        .addSnapshotListener { snapshot, error ->
                            val response = if (snapshot != null) {

                                val data = snapshot.toObjects(CardModule::class.java)
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

    override suspend fun addCardToFireStore(
        question: String,
        corAnswer: String,
        cardsRef: CollectionReference,
        onComplete: (Boolean) -> Unit
    ) {
        val documentId = cardsRef.document().id

        val cardsModule = CardModule(
            documenId = documentId,
            question = question,
            corAnswer = corAnswer,

            )
        cardsRef.document(documentId)
            .set(cardsModule)
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }

    }


}
