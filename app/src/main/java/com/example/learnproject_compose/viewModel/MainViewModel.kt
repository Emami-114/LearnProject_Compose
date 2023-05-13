package com.example.learnproject_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.QuizRepository
import com.example.learnproject_compose.local.Databases
import com.example.learnproject_compose.model.QuizModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuizRepository, private val db: Databases
) : ViewModel() {





     val quizListNeue = repository.getLimit()




    private val _quizList: MutableState<List<QuizModel>> = mutableStateOf(repository.loadQuiz())
    val quizList: State<List<QuizModel>> = _quizList


    val questionIndex = mutableStateOf(0)



    val filterList = repository.quizFilterList

    init {
        get()
//        getAllQuiz()
        getAllItems()

    }


    val items = mutableStateOf<List<QuizModel>>(listOf())


    private fun getAllItems() {
        viewModelScope.launch {
            val itemsList = db.quizDao.getRandom()
            items.value = itemsList
        }
    }


    fun get() = viewModelScope.launch {
        try {
            repository.getAllQuiz()
            repository.getAllSucsses()
            repository.inserAllToDatabase()
        } catch (e: Exception) {

        }

    }


    val zahl = mutableStateOf<Int>(0)

    fun getAllQuiz() = viewModelScope.launch {
//            repository.insertTodb()
//            _quizList.value = repository.loadQuiz()

//            filterList.value = repository.quizFilterList.value

//        val quiz = repository.getLimit()
//        quizAll.compareAndSet(quiz, quiz)
    }


    fun checkAnswer() {
//        val answerList = currenzQuiz.value.answer
        viewModelScope.launch {
//            delay(1000)
//            _currenQuiz.value = quizList.value[questionIndex.value]
            questionIndex.value = questionIndex.value + 1

        }
    }

    fun update(isSucsses: Boolean, id: Int) = viewModelScope.launch {
        try {
            repository.update(isSucsses, id)

        } catch (e: Exception) {

        }
    }
}


