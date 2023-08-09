package com.example.learnproject_compose.screen.home.slider

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SliderViewModel @Inject constructor(
    private val repo: SliderWordRepo
) : ViewModel() {
//    @Inject
//    lateinit var missingWordViewModel: MissingWordViewModel

    var wordsList by mutableStateOf<Resources2<List<SliderWordModel>>>(Resources2.Loading())
        private set


    private val userLevel = "A1"


    init {
        getSliderWordsFromFireStore(userLevel)

    }


    fun getSliderWordsFromFireStore(level: String?) = viewModelScope.launch {
        val sliderWordRef = Firebase.firestore.collection("sliderWord")
        repo.getSliderWordsFromFireStore(level, sliderWordRef).collect {
            wordsList = it
        }
    }

}