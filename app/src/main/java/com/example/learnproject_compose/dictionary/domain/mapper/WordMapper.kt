package com.example.learnproject_compose.dictionary.domain.mapper

import com.example.learnproject_compose.dictionary.dictionaryModel.DefinitionDto
import com.example.learnproject_compose.dictionary.dictionaryModel.MeaningDto
import com.example.learnproject_compose.dictionary.dictionaryModel.WordDataDto
import com.example.learnproject_compose.dictionary.domain.model.Definition
import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import java.util.Date

fun DefinitionDto.toDefinition(): Definition {
    return Definition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )
}

fun MeaningDto.toMeaning(): Meaning {
    return Meaning(
        definitions = definitions?.map { it.toDefinition() }, partOfSpeech = partOfSpeech
    )
}

fun WordDataDto.toWordData(): WordData {
    return WordData(word = word,
        phonetic = phonetics?.mapNotNull { it.text }?.toSet()?.joinToString(", ")?.replace("/", ""),
        sourceUrls = sourceUrls,
        meanings = meanings?.map { it.toMeaning() })
}

fun WordData.toWordDataEntity(): WordDataEntity {
    return WordDataEntity(
        word = word,
        phonetic = phonetic,
        meanings = meanings,
        sourceUrls = sourceUrls,
        date = Date()
    )
}