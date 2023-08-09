package com.example.learnproject_compose.dictionary.presentation.savedWords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.dictionary.domain.WordOrder
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.presentation.savedWords.components.OrderRadioButtons
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.ui.theme.backgroundWhite

@Composable
fun SavedWordPage(navController: NavController) {
    Column(modifier = Modifier.background(backgroundWhite)) {

        SavedWordsScreen()
    }

}

@Composable
fun SavedWordsScreen(viewModel: SaveWordsViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState().value
    val wordQury = remember { mutableStateOf("") }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(20.dp)
        ) {
            SearchBarSaved(wordQury, viewModel, state.wordOrder)
            Spacer(modifier = Modifier.height(15.dp))
            OrderRadioButtons(wordOrder = state.wordOrder, onOrderChange = { wordOrder ->
                viewModel.getWords(wordOrder, wordQury.value)
            })
            SavedWordList(
                viewModel = viewModel,
                wordDataEntityItems = state.filteredWordDataEntityItem
            )

        }
        when {
            state.isEmpty -> Text(
                text = "Keine Daten gefunden! :(", modifier = Modifier.align(
                    Alignment.Center
                ), color = TextWhite
            )
        }

    }
}

@Composable
fun SearchBarSaved(
    wordQuery: MutableState<String>,
    viewModel: SaveWordsViewModel,
    wordOrder: WordOrder
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Search") },
        shape = RoundedCornerShape(10.dp),
        value = wordQuery.value,
        onValueChange = { text ->
            viewModel.getWords(wordOrder, text)
            wordQuery.value = text
        },
        maxLines = 1,
        placeholder = { Text(text = "Geben Sie ein Wort ein...", color = TextWhiteDarke) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null, tint = TextWhiteDarke
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = LightGreen1,
            focusedBorderColor = LightGreen1,
            unfocusedBorderColor = TextWhiteDarke,
            unfocusedLabelColor = TextWhiteDarke,
            textColor = TextWhite
        )
    )
}

@Composable
fun SavedWordList(viewModel: SaveWordsViewModel, wordDataEntityItems: List<WordDataEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(wordDataEntityItems.size) { i ->
            SavedWordsItem(
                wordDataEntity = wordDataEntityItems[i],
                onBookmarkClicked = { clickedWord ->
                    clickedWord.let { wordDataEntity ->
                        viewModel.removeWord(wordDataEntity)
                    }
                })
        }
    }

}
