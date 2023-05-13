package com.example.learnproject_compose.dictionary.dictionaryModel

data class DefinitionDto(
    val antonyms: List<String>?,
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)