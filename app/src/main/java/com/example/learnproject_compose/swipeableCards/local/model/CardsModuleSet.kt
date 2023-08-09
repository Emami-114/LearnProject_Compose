package com.example.learnproject_compose.swipeableCards.local.model

import com.google.firebase.Timestamp

data class CardsModuleSet(
    val userId: String = "",
    val title: String = "",
    val level: String = "",
    val color: Int = 0,
    val timestamp: Timestamp = Timestamp.now(),
    val documentId: String = "",
    val userName: String = "",
    val active: Boolean = false,
    val new: Boolean = true,
    val itemCount: Int = 0
)