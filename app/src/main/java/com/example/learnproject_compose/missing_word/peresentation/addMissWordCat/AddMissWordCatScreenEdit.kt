package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components.ExpoDropDownMe
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen.ColorList
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.util.noRippleClickable
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMissWordCatPageEdit(navController: NavController, missCatId: String?) {
    val missWordViewModel: MissingWordCatViewModel = hiltViewModel()

    androidx.compose.material3.Scaffold(topBar = {
        TopBarSelb(title = "Lernset bearbeiten") {
            navController.navigate(DetailsScreen.HOME2.route)
        }
    }) {

        val colorList = ColorList.listColor

        Surface(modifier = Modifier.padding(it)) {

            AddMissWordCatScreenEdit(
                missingWordCatViewModel = missWordViewModel,
                navController, missCatId = missCatId, colorList = colorList
            )

        }

    }

}


@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "UnrememberedMutableState"
)
@Composable
fun AddMissWordCatScreenEdit(
    missingWordCatViewModel: MissingWordCatViewModel?,
    navController: NavController,
    colorList: List<Color>,
    missCatId: String?
) {

    val context = LocalContext.current

    val addMissUiState = missingWordCatViewModel?.addMissWordCatUiState
        ?: AddMissWordUiState()


    val isMissCatIdNotBlank = !missCatId.isNullOrBlank()

    Log.d("MYTAG", "Is Id Leer: $isMissCatIdNotBlank")

    LaunchedEffect(key1 = Unit) {
        if (isMissCatIdNotBlank) {
            if (missCatId != null) {
                missingWordCatViewModel?.getMissWordCatSingle(missCatId)
            }

        } else {
            missingWordCatViewModel?.resetMissWordState()
        }
    }

    var selectedColor by remember { mutableStateOf(colorList[addMissUiState.color ?: 0]) }


    val scope = rememberCoroutineScope()
    val scafolState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scafolState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(top = 10.dp)
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (addMissUiState.addStatus) {
                scope.launch {
//                    scafolState.snackbarHostState.showSnackbar("Erfolgreich hinzugefügt.")
                    Toast.makeText(context, "Erfolgreich hinzugefügt.", Toast.LENGTH_SHORT).show()

                    missingWordCatViewModel?.resetMissWordState()

//                    onNavigate.invoke()
                }
            }
            if (addMissUiState.updateMissWordCatStatus) {
                scope.launch {
                    missingWordCatViewModel?.resetMissWordState()
//                    onNavigate.invoke()

                }
            }
            OutlinedTextField(
                value = addMissUiState.title,
                onValueChange = { missingWordCatViewModel?.onTitleChange(it) },
                label = { Text(text = "Name des Lernsets") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGreen1,
                    focusedLabelColor = LightGreen1,
                    unfocusedBorderColor = TextWhiteDarke,
                    unfocusedLabelColor = TextWhiteDarke,
                    textColor = TextWhite,
                    cursorColor = LightGreen1
                )
            )
            OutlinedTextField(
                value = addMissUiState.description,
                onValueChange = { missingWordCatViewModel?.onDescription(it) },
                label = { Text(text = "Beschreibung") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                maxLines = 3,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGreen1,
                    focusedLabelColor = LightGreen1,
                    unfocusedBorderColor = TextWhiteDarke,
                    unfocusedLabelColor = TextWhiteDarke,
                    textColor = TextWhite,
                    cursorColor = LightGreen1
                )
            )
//            Spacer(modifier = Modifier.height(10.dp))

            ExpoDropDownMe(onLevelChange = { missingWordCatViewModel?.onLevelChange(it) })
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(.7.dp, color = TextWhiteDarke),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 8.dp)
                ) {
                    itemsIndexed(colorList) { index, color ->
                        var selected = mutableStateOf(color == selectedColor)

                        ColorItem(color = color, selected = selected.value) {
                            missingWordCatViewModel?.onColorChange(index)

                            selectedColor = colorList[index]

                        }
                    }
                }


            }
            Spacer(modifier = Modifier.height(17.dp))



            Row(
                modifier = Modifier
                    .padding(padding)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, color = TextWhiteDarke),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .weight(9f)
                        .padding(),
                    text = "Veröffentlich",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite.copy(alpha = .7f),
                )
                Switch(
                    modifier = Modifier.weight(1f),
                    checked = addMissUiState.active,
                    onCheckedChange = {
                        missingWordCatViewModel?.onIsActive(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = LightGreen1,
                        uncheckedThumbColor = Color.Gray)                )

            }
            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .padding(padding)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, color = TextWhiteDarke),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .weight(9f)
                        .padding(),
                    text = "Neu",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite.copy(alpha = .7f),
                )
                Switch(
                    modifier = Modifier.weight(1f),
                    checked = addMissUiState.new,
                    onCheckedChange = {
                        missingWordCatViewModel?.onNewChange(it)
                    },
                    colors = SwitchDefaults.colors(checkedThumbColor = LightGreen1, uncheckedThumbColor = Color.Gray)
                )

            }
            Spacer(modifier = Modifier.height(12.dp))


            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(AppTheme.dimens.smallMedium),
                onClick = {
                    if (isMissCatIdNotBlank) {

                        missingWordCatViewModel?.updateMissingCat(missCatId ?: "", navController)

                }},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorList.listColor[addMissUiState.color])
            ) {
                Text(text = "Erstellen", color = TextWhite, fontSize = 20.sp)
            }


        }
    }
}


