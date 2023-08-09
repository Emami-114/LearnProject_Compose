package com.example.learnproject_compose.deutschTest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeQuiz(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val question: String? = null,
    val corAnswer: String? = null,
    var answer1: String? = null,
    var answer2: String? = null,
    var answer3: String? = null,
    var url: String? = null,
    var isCorOrNot: Boolean? = null,
    var isFavors: Boolean? = null,
    )
