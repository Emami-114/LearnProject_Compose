package com.example.learnproject_compose.dictionary.data.local

import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.domain.repository.SaveWordsRepo
import kotlinx.coroutines.flow.Flow

class SavedWordsRepoImpl(private val dao: SaveWordsDao) : SaveWordsRepo {
    override fun getSaveWords(): Flow<List<WordDataEntity>> {
        return dao.getSavedWords()
    }

    override suspend fun insertIntoSaved(wordDataEntity: WordDataEntity) {
        return dao.insertIntoSaved(wordDataEntity)
    }

    override suspend fun deleteFromSaved(
        word: String?,
        phonetic: String?,
        meanings: List<Meaning>?
    ) {
        return dao.deleteFromSaved(word = word, phonetic = phonetic, meanings = meanings)
    }

    override suspend fun isExistWord(
        word: String?,
        phonetic: String?,
        meanings: List<Meaning>?
    ): Boolean {
        return dao.isExistWord(word, phonetic, meanings)
    }

}