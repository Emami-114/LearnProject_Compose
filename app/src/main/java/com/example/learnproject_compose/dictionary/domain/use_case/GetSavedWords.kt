package com.example.learnproject_compose.dictionary.domain.use_case

import com.example.learnproject_compose.dictionary.domain.WordOrder
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.domain.repository.SaveWordsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSavedWords(val repo: SaveWordsRepo) {
    operator fun invoke(wordOrder: WordOrder = WordOrder.Descending): Flow<List<WordDataEntity>> {
        return repo.getSaveWords().map { wordEntities ->
            when (wordOrder) {
                is WordOrder.Ascending -> wordEntities.sortedBy { it.date }
                is WordOrder.Descending -> wordEntities.sortedByDescending { it.date }
            }
        }
    }
}