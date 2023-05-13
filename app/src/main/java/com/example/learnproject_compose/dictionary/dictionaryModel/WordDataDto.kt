package com.example.learnproject_compose.dictionary.dictionaryModel

data class WordDataDto(
    val word: String,
    val phonetics: List<PhoneticDto>?,
    val meanings: List<MeaningDto>?,
    val license: LicenseDto?,
    val sourceUrls: List<String>?
)
