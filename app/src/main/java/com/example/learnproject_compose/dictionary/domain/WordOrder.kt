package com.example.learnproject_compose.dictionary.domain

sealed class WordOrder {
    object Ascending : WordOrder()
    object Descending : WordOrder()

}
