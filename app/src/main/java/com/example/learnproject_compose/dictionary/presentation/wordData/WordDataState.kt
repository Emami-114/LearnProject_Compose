package com.example.learnproject_compose.dictionary.presentation.wordData

import com.example.learnproject_compose.dictionary.domain.model.WordData


data class WordDataState(
    val wordDataItems: List<WordData> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)