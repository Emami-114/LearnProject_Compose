package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.util.noRippleClickable

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExpoDropDownMe(onLevelChange: (String) -> Unit) {
    val options = listOf("A1", "A2", "B1", "B2", "C1", "C2")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText = remember {

        mutableStateOf("")
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }


    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(10.dp)) {

        androidx.compose.material3.OutlinedTextField(enabled = false, modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            }
            .noRippleClickable { expanded = !expanded },
            textStyle = androidx.compose.material.MaterialTheme.typography.h6,
            readOnly = true,
            value = selectedOptionText.value,
            onValueChange = {
                selectedOptionText.value = it
            },


            label = {
                androidx.compose.material3.Text(
                    text = "Niveau",
                    style = androidx.compose.material.MaterialTheme.typography.subtitle2
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier.noRippleClickable { expanded = !expanded },
                    tint = TextWhite
                )
            },
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                textColor = TextWhite,
                disabledTextColor = TextWhite, disabledBorderColor = TextWhiteDarke,
                disabledLabelColor = TextWhiteDarke,
            )
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(DeepBlue)
                .width(
                    with(LocalDensity.current) { textFieldSize.width.toDp() }
                )) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onLevelChange.invoke(option)
                    selectedOptionText.value = option
                    expanded = false

                }) {
                    androidx.compose.material3.Text(
                        text = option,
                        color = TextWhite,
                        style = androidx.compose.material.MaterialTheme.typography.h6
                    )
                }
                DrawDottedLine()
            }

        }
    }
}


//    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//
//
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded }) {
//            OutlinedTextField(modifier = Modifier
//                .padding(10.dp)
//                .fillMaxWidth()
//                .onGloballyPositioned {
//                    textFieldSize = it.size.toSize()
//                },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Outlined.Menu,
//                        contentDescription = null,
//                        tint = TextWhiteDarke
//                    )
//                },
//                readOnly = true,
//                textStyle = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = TextWhite
//                ),
//                value = selectedOptionText,
//                onValueChange = {},
//                label = {
//                    Text(
//                        text = "Niveau",
//                    )
//                }, placeholder = { Text(text = "Niveau") },
//                trailingIcon = {
//                    ExposedDropdownMenuDefaults.TrailingIcon(
//                        expanded = expanded
//                    )
//                },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = MaterialTheme.colorScheme.primary,
//                    focusedLabelColor = MaterialTheme.colorScheme.primary,
//                    unfocusedBorderColor = TextWhiteDarke,
//                    unfocusedLabelColor = TextWhiteDarke,
//                    textColor = TextWhite,
//                    cursorColor = MaterialTheme.colorScheme.primary
//                )
//            )
//            ExposedDropdownMenu(modifier = Modifier.width(with(LocalAppDimens.current) { textFieldSize.width.toDp() }),
//                expanded = expanded,
//                onDismissRequest = { expanded = false }) {
//                options.forEach { selectionOption ->
//                    DropdownMenuItem(onClick = {
//                        selectedOptionText = selectionOption
////                        missingWordCatViewModel?.onLevelChange(selectionOption)
//                        onLevelChange.invoke(selectionOption)
//                        expanded = false
//                    }) {
//                        Text(text = selectionOption, fontSize = 18.sp, color = TextBlack2)
//
//
//                    }
//                    DrawDottedLine()
//                }
//
//            }
//
//        }
//    }



