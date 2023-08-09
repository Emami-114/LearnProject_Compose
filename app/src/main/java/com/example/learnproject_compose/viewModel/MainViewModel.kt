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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuizRepository, private val db: Databases
) : ViewModel() {

    private var _sharedFlow = MutableSharedFlow<List<QuizModel>>()
    val sharedFlow = _sharedFlow.asSharedFlow()

}


