package com.example.learnproject_compose.dictionary.domain.repository

import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.util.DataStatus
import kotlinx.coroutines.flow.Flow

interface WordDataRepo {
    fun getWordData(word: String): Flow<DataStatus<List<WordData>>>
}