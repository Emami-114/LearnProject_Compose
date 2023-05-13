package com.example.learnproject_compose.local

import androidx.compose.runtime.State
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.learnproject_compose.model.QuizModel
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    // TODO: Insert

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<QuizModel>)

    // TODO: getAll
    @Query("SELECT * FROM quiz_table ORDER BY RANDOM()")
     fun getAll(): Flow<List<QuizModel>>

    // TODO: get Limit Order By Random
    @Query("SELECT * FROM quiz_table ORDER BY RANDOM() LIMIT 33")
    suspend fun getRandom(): List<QuizModel>


    @Query("SELECT * FROM quiz_table WHERE isSuccess=:sucsses")
    suspend fun getAllSucsses(sucsses: Int): List<QuizModel>

    @Query("UPDATE quiz_table SET isSuccess =:sucsses  WHERE id=:id")
    suspend fun update(id: Int, sucsses: Boolean)

    @Delete
    fun delete(quizModel: QuizModel)

}