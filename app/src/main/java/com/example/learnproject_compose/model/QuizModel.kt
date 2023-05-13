package com.example.learnproject_compose.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "quiz_table")
data class QuizModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val question: String,
    val answerCorrect: String,
    var answer: String,
    var answer2: String,
    var answer3: String,
    var isSuccess: Boolean? = null

)
