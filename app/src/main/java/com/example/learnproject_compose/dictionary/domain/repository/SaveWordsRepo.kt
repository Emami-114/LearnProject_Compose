package com.example.learnproject_compose.dictionary.domain.repository

import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import kotlinx.coroutines.flow.Flow

interface SaveWordsRepo {
    fun getSaveWords(): Flow<List<WordDataEntity>>

    suspend fun insertIntoSaved(wordDataEntity: WordDataEntity)

    suspend fun deleteFromSaved(word: String?, phonetic: String?, meanings: List<Meaning>?)

    suspend fun isExistWord(word: String?, phonetic: String?, meanings: List<Meaning>?): Boolean
}