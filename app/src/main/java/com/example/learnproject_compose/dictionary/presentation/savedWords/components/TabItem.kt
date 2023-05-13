package com.example.learnproject_compose.dictionary.presentation.savedWords.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.dictionary.presentation.savedWords.SavedWordPage
import com.example.learnproject_compose.dictionary.presentation.wordData.WordDataPage
import com.example.learnproject_compose.screen.home.HomeScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {

    object Dictionary : TabItem(
        R.drawable.ic_book_24,
        "Wörterbuch",
        { WordDataPage(navController = rememberNavController()) })

    object DictionaryFavorite : TabItem(R.drawable.ic_bookmarks_24, "Favoriten", {
        SavedWordPage(
            navController = rememberNavController()
        )
    })

}