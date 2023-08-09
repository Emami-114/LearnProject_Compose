package com.example.learnproject_compose

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.local.Databases
import com.example.learnproject_compose.model.QuizModel
import javax.inject.Inject

class QuizRepository @Inject constructor(private val db: Databases) {

}