package com.example.learnproject_compose.missing_word.local.model

import com.google.firebase.Timestamp

data class MissingWordModel(
    val documenId: String? = null,
    val question: String? = null,
    val corAnswer: String? = null,
    val answer1: String? = null,
    val answer2: String? = null,
    val answer3: String? = null,

    )
