package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat

import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.google.firebase.Timestamp

data class AddMissWordUiState(
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val level: String = "",
    val active: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
    val documentId: String = "",
    val new: Boolean = true,
    val color: Int = 0,
    val userName: String = "",
    val addStatus: Boolean = false,
    val selectedMissWordCat: MissWordCat? = null,
    val updateMissWordCatStatus: Boolean = false,
    val updateMissWordCatStatusNew: Boolean = false,
    val deleteStatus: Boolean = false

)
