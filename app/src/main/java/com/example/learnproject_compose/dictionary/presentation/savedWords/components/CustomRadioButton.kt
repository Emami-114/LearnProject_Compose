package com.example.learnproject_compose.dictionary.presentation.savedWords.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.ButtonBlue
import com.example.learnproject_compose.ui.theme.LightGreen1

@Composable
fun CustomRadioButton(
    icon: ImageVector, selected: Boolean, onSelect: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelect() }) {
//        RadioButton(selected = selected, onClick = onSelect)
////        Text(text = text)
        IconButton(onClick = onSelect) {
            Icon(imageVector = icon, contentDescription = null, tint = if (selected) ButtonBlue else Color.Gray)

        }

    }

}