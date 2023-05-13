package com.example.learnproject_compose.dictionary.presentation.savedWords.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.dictionary.domain.WordOrder

@Composable
fun OrderRadioButtons(wordOrder: WordOrder, onOrderChange: (WordOrder) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        CustomRadioButton(
            icon = Icons.Default.ArrowUpward,
            selected = wordOrder is WordOrder.Ascending,
            onSelect = { onOrderChange(WordOrder.Ascending) })
        Spacer(modifier = Modifier)
        CustomRadioButton(
            icon = Icons.Default.ArrowDownward,
            selected = wordOrder is WordOrder.Descending,
            onSelect = { onOrderChange(WordOrder.Descending) })

    }

}