package com.example.learnproject_compose.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SaveWordsDao {
    @Query("SELECT * FROM worddataentity")
    fun getSavedWords(): Flow<List<WordDataEntity>>

    @Query("SELECT EXISTS (SELECT * FROM WordDataEntity WHERE word=:word AND phonetic=:phonetic AND meanings=:meanings)")
    suspend fun isExistWord(word: String?, phonetic: String?, meanings: List<Meaning>?): Boolean

    @Query("DELETE FROM WordDataEntity WHERE word=:word AND phonetic=:phonetic AND meanings=:meanings")
    suspend fun deleteFromSaved(word: String?, phonetic: String?, meanings: List<Meaning>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoSaved(wordDataEntity: WordDataEntity)

}