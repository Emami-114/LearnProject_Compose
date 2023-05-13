package com.example.learnproject_compose.dictionary.dictionaryModel

data class PhoneticDto(
    val audio: String?,
    val license: LicenseDto?,
    val sourceUrl: String?,
    val text: String?
)