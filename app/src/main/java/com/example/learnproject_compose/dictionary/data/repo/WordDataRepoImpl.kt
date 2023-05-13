package com.example.learnproject_compose.dictionary.data.repo

import com.example.learnproject_compose.dictionary.data.local.SaveWordsDao
import com.example.learnproject_compose.dictionary.domain.mapper.toWordData
import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.domain.repository.WordDataRepo
import com.example.learnproject_compose.dictionary.remote.DictionaryApiService
import com.example.learnproject_compose.dictionary.util.DataStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordDataRepoImpl(
    private val dictionaryApiService: DictionaryApiService,
    private val dao: SaveWordsDao
) : WordDataRepo {
    override fun getWordData(word: String): Flow<DataStatus<List<WordData>>> = flow {
        emit(DataStatus.Loading())
        try {
            val wordDates = dictionaryApiService.getWordData(word = word).map { it.toWordData() }
            wordDates.forEach { wordDataItem ->
                wordDataItem.isSaved =
                    dao.isExistWord(wordDataItem.word, wordDataItem.phonetic, wordDataItem.meanings)
            }
            emit(DataStatus.Success(wordDates))

        } catch (e: Exception) {
            emit(DataStatus.Error(e.message))
        }


    }
}