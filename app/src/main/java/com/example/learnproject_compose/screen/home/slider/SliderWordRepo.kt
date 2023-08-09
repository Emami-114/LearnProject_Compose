package com.example.learnproject_compose.screen.home.slider

import android.annotation.SuppressLint
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.random.Random

class SliderWordRepo @Inject constructor() {


    @SuppressLint("SuspiciousIndentation")
    suspend fun getSliderWordsFromFireStore(
        level: String?,
        sliderWordRef: CollectionReference
    ): Flow<Resources2<List<SliderWordModel>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null
        val randomValue = Random.nextDouble()
        try {
            snapshotStateListener = sliderWordRef
                .whereEqualTo("level", level)
                .orderBy(FieldPath.documentId())
                .startAt(generateRandomDocumentId())
                .limit(1)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {

                        val data = snapshot.toObjects(SliderWordModel::class.java)

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


        awaitClose {
            snapshotStateListener?.remove()
        }

    }

    // Hilfsmethode zur Generierung einer zufälligen Document ID
    private fun generateRandomDocumentId(): String {
        val characters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(20) { characters.random() }.joinToString("")
    }

}