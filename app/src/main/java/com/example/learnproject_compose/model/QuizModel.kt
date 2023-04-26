package com.example.learnproject_compose.model

data class QuizModel(
    val id: Int,
    val question: String,
    val answerCorrect: String,
    val answer1: String,
    val answer2: String,
    val answer3: String
)
