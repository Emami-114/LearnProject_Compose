package com.example.learnproject_compose.dictionary.presentation.savedWords

import com.example.learnproject_compose.dictionary.domain.WordOrder
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity

data class SavedWordsState(
    var wordDataEntityItems: List<WordDataEntity> = emptyList(),
    val filteredWordDataEntityItem: List<WordDataEntity> = emptyList(),
    val isEmpty: Boolean = false,
    val wordOrder: WordOrder = WordOrder.Descending
)