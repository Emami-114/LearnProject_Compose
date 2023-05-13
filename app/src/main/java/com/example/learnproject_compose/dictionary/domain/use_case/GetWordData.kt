package com.example.learnproject_compose.dictionary.domain.use_case

import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.domain.repository.WordDataRepo
import com.example.learnproject_compose.dictionary.util.DataStatus
import kotlinx.coroutines.flow.Flow

class GetWordData(private val repo: WordDataRepo) {
    operator fun invoke(word: String): Flow<DataStatus<List<WordData>>>? {
        if (word.isNotBlank()) {
            return repo.getWordData(word.trim())
        }
        return null
    }
}