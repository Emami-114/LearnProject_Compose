package com.example.learnproject_compose.dictionary.domain.use_case

import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.repository.SaveWordsRepo

class RemoveFromSaved(val repo: SaveWordsRepo) {
    suspend operator fun invoke(word: String?, phonetic: String?, meanings: List<Meaning>?) {
        repo.deleteFromSaved(word, phonetic, meanings)
    }
}