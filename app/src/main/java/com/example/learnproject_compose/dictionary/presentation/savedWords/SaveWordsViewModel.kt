package com.example.learnproject_compose.dictionary.presentation.savedWords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.dictionary.domain.WordOrder
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.domain.use_case.GetSavedWords
import com.example.learnproject_compose.dictionary.domain.use_case.RemoveFromSaved
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveWordsViewModel @Inject constructor(
    private val getSavedWords: GetSavedWords,
    private val removeFromSaved: RemoveFromSaved
) : ViewModel() {
    private val _state = MutableStateFlow(SavedWordsState())
    val state = _state.asStateFlow()
    private var job: Job? = null


    init {
        getWords(WordOrder.Descending)
    }

    fun getWords(wordOrder: WordOrder, wordQuery: String = "") {
        job?.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {
            getSavedWords(wordOrder).collectLatest { wordDataEntities ->
                _state.update {
                    val filteredWordDataEntityItems = setFilteredList(wordDataEntities, wordQuery)
                    _state.value.copy(
                        wordDataEntityItems = wordDataEntities,
                        filteredWordDataEntityItem = filteredWordDataEntityItems,
                        isEmpty = wordDataEntities.isEmpty() || filteredWordDataEntityItems.isEmpty(),
                        wordOrder = wordOrder
                    )
                }
            }
        }
    }

    private fun setFilteredList(
        wordDataEntities: List<WordDataEntity>,
        wordQuery: String
    ): List<WordDataEntity> {
        return wordDataEntities.filter { wordDataEntity ->
            wordDataEntity.word.contains(wordQuery, ignoreCase = true)
        }
    }

    fun removeWord(wordDataEntity: WordDataEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            with(wordDataEntity) {
                removeFromSaved(word, phonetic, meanings)
            }
        }
    }
}