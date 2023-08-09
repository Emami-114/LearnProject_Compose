package com.example.learnproject_compose.deutschTest.data.repository

import android.util.Log
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.deutschTest.domain.repository.DeQuizRepository
import com.example.learnproject_compose.local.Databases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeQuizRepositoryImpl @Inject constructor(
    private val deQuizRef: CollectionReference,
    private val db: Databases
) :
    DeQuizRepository {

    override fun getQuizFormFireStore(): Flow<Resources2<List<DeQuiz>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null
        try {
            snapshotStateListener = deQuizRef.orderBy("id")
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val notes = snapshot.toObjects(DeQuiz::class.java)
                        Resources2.Success(data = notes)
                    } else {
                        Resources2.Error(throwable = error?.cause)
                    }
                    trySend(response)
                }
        } catch (e: Exception) {
            trySend(Resources2.Error(e.cause))
            e.printStackTrace()
        }
        awaitClose {
            snapshotStateListener?.remove()
        }
    }

    override suspend fun getDeQuizRandomLimit(limit:Int): Flow<Resources2<List<DeQuiz>>> =
        callbackFlow {

            try {
                val data = db.deQuizDao.getDeQuizRandomLimit(limit)
                trySend(Resources2.Success(data = data))

            } catch (e: Exception) {
                trySend(Resources2.Error(throwable = e))
            }
            awaitClose()

        }

    override suspend fun getDeQuizRange(startId: Int, endId: Int)
    : Flow<Resources2<List<DeQuiz>>> = callbackFlow{


            try {
                val data = db.deQuizDao.getDeQuizRange(startId, endId)
                trySend(Resources2.Success(data = data))

            } catch (e: Exception) {
                trySend(Resources2.Error(throwable = e))
            }
            awaitClose()



    }

    override suspend fun getAllCoreOrNot(isCorOrNot: Int): Flow<Resources2<List<DeQuiz>>> =
        callbackFlow {
            try {
                val data = db.deQuizDao.getAllCoreOrNot(isCorOrNot)
                trySend(Resources2.Success(data = data))
            } catch (e: Exception) {
                Log.d("MYTAG", "Repository getAllCoreOrNot:  ${e.message}")
                trySend(
                    Resources2.Error(throwable = e)
                )
            }

            awaitClose()
        }

    override suspend fun updateDeQuiz(id: String, isCorOrNot: Boolean) =
        db.deQuizDao.updateDeQuiz(id, isCorOrNot)




    override suspend fun getAllFavorite(favorite: Boolean): Flow<Resources2<List<DeQuiz>>> = callbackFlow {

            try {
                val data = db.deQuizDao.getAllIsFavorite(favorite)
                trySend(Resources2.Success(data = data))
            } catch (e: Exception) {
                Log.d("MYTAG", "Repository getAllFavorite:  ${e.message}")
                trySend(
                    Resources2.Error(throwable = e)
                )
            }

            awaitClose()
        }

    override suspend fun updateDeQuizFavorite(id: String, isFavors: Boolean) =
        db.deQuizDao.updateDeQuizFavorite(id = id,isFavors)


}