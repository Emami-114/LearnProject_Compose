package com.example.learnproject_compose.model

data class QuizModel(
    val id: Int,
    val question: String,
    val answerCorrect: String,
    val answer: List<String>,
    val isSuccess : Boolean = false

)
