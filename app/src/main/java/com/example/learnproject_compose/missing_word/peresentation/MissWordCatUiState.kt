package com.example.learnproject_compose.missing_word.peresentation

import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel

data class MissWordCatUiState(
    var missingWorCatdList: Resources2<List<MissWordCat>> = Resources2.Loading(),
)

data class MissWordUiState(
    var missingWordList: Resources2<List<MissingWordModel>> = Resources2.Loading(),
)
