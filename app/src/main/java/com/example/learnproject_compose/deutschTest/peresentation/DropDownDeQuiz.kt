package com.example.learnproject_compose.deutschTest.peresentation

import android.content.Context
import android.preference.PreferenceManager
import android.provider.Settings.Global.putString
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.content.edit
import androidx.lifecycle.SavedStateHandle
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownDeQuiz(viewModel: DeQuizViewModel) {
    val context = LocalContext.current


    var expanded by remember { mutableStateOf(false) }
    val bundList = listOf(
        "Baden-Württemberg", "Bayern",
        "Berlin", "Brandenburg","Bremen","Hamburg","Hessen","Mecklenburg-Vorpommern",
        "Niedersachsen",
        "Nordrhein-Westfalen","Rheinland-Pfalz","Saarland",
        "Sachsen","Sachsen-Anhalt","Schleswig-Holstein","Thüringen"
    )

    var selectedBund by rememberSaveable {
        mutableStateOf(
            BundPreferences.getSelectedBund(context) ?: ""
        )
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    LaunchedEffect(Unit) {
        BundPreferences.saveSelectedBund(context, selectedBund)
    }
//    viewModel.bundQuiz(selectedBund)



    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(enabled = false,modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            }
            .noRippleClickable { expanded = !expanded },
            textStyle = MaterialTheme.typography.h6,
            readOnly = true,
            value = selectedBund,
            onValueChange = {
                selectedBund = it
            },


            label = {
                Text(
                    text = "Ihr Bundesland",
                    color = TextWhite,
                    style = MaterialTheme.typography.subtitle2
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier.noRippleClickable { expanded = !expanded }, tint = TextWhite
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = TextWhite,
                disabledTextColor = TextWhite, disabledBorderColor = TextWhiteDarke,
                disabledLabelColor = TextWhiteDarke,)
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(largeRadialGradient).width(
                with(LocalDensity.current) { textFieldSize.width.toDp() }
            )) {
            bundList.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedBund = label
                    expanded = false
                }) {
                    Text(text = label, color = TextWhite, style = MaterialTheme.typography.h6)
                }
            }

        }

    }
}

object BundPreferences {
    private const val KEY_SELECTED_BUND = "selected_bund"
    fun saveSelectedBund(context: Context, bund: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit {
            putString(KEY_SELECTED_BUND, bund)
        }
    }

    fun getSelectedBund(context: Context): String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(KEY_SELECTED_BUND, null)
    }
}