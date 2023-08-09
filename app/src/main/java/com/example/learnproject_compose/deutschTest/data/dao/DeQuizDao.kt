package com.example.learnproject_compose.deutschTest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.model.QuizModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DeQuizDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeQuiz(list: List<DeQuiz>)


    @Query("SELECT * FROM dequiz WHERE CAST (id AS INTEGER) BETWEEN :startId AND :endId ORDER BY RANDOM() LIMIT :limit")
    suspend fun getDeQuizRandomLimit(limit: Int,startId: Int = 1, endId: Int = 300): List<DeQuiz>


    @Query("SELECT * FROM dequiz WHERE CAST (id AS INTEGER) BETWEEN :startId AND :endId")
    suspend fun getDeQuizRange(startId:Int,endId:Int): List<DeQuiz>


    @Query("SELECT * FROM dequiz WHERE isCorOrNot=:isCorOrNot")
    suspend fun getAllCoreOrNot(isCorOrNot: Int): List<DeQuiz>


    @Query("SELECT * FROM dequiz WHERE isFavors=:favorite")
    suspend fun getAllIsFavorite(favorite: Boolean): List<DeQuiz>

    @Query("UPDATE dequiz SET isFavors =:isFaorite  WHERE id=:id")
    suspend fun updateDeQuizFavorite(id: String?, isFaorite: Boolean)

    @Query("UPDATE dequiz SET isCorOrNot =:isCorOrNot  WHERE id=:id")
    suspend fun updateDeQuiz(id: String?, isCorOrNot: Boolean)

}