package com.example.learnproject_compose.dictionary.domain.use_case

import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.domain.repository.SaveWordsRepo

class AddIntoSaved(val repo: SaveWordsRepo) {
    suspend operator fun invoke(wordDataEntity: WordDataEntity) {
        repo.insertIntoSaved(wordDataEntity)
    }
}