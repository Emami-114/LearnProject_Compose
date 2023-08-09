package com.example.learnproject_compose.swipeableCards.peresent.cardsSet

import android.annotation.SuppressLint
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
fun AddCardsSetPage(navController: NavController) {
    val cardsViewMidel: CardsSetViewModel = hiltViewModel()

    androidx.compose.material3.Scaffold(topBar = {
        TopBarSelb(title = "Karteikarten erstellen") {
            navController.navigate(DetailsScreen.HOME2.route)
        }
    }) {

        val colorList = ColorList.listColor
        Surface(modifier = Modifier.padding(it)) {

            AddCardsSetScreen(
                cardsSetViewModel = cardsViewMidel,
                navController, colorList = colorList
            )

        }

    }

}


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "UnrememberedMutableState", "SuspiciousIndentation"
)
@Composable
fun AddCardsSetScreen(
    cardsSetViewModel: CardsSetViewModel?,
    navController: NavController,
    colorList: List<Color>,
) {


    val context = LocalContext.current

    val addCardSetUiState = cardsSetViewModel?.addCardsSetUiState
        ?: CardsSetUiState()

    val isFormsNotBlank = addCardSetUiState.title.isNotEmpty()



    val scope = rememberCoroutineScope()
    val scafolState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scafolState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .padding(AppTheme.dimens.smallMedium)
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (addCardSetUiState.addStatus) {
                scope.launch {

                    Toast.makeText(context, "Erfolgreich hinzugefügt.", Toast.LENGTH_SHORT).show()

                    cardsSetViewModel?.resetCardsSetState()

                }
            }

            OutlinedTextField(
                value = addCardSetUiState.title,
                onValueChange = { cardsSetViewModel?.onTitleChange(it) },
                label = { Text(text = "Name des Lernsets",
                    style = MaterialTheme.typography.subtitle2)
                        },
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


            var selectedColor by remember { mutableStateOf(colorList[addCardSetUiState.color])}


            ExpoDropDownMe(onLevelChange = {cardsSetViewModel?.onLevelChange(it)})
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

                var selected = mutableStateOf( color == selectedColor)

                        ColorItem(color = color, selected = selected.value) {
//                            selected = !selected
                            selectedColor = colorList[index]

                            cardsSetViewModel?.onColorChange(index)

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
                    checked = addCardSetUiState.active,
                    onCheckedChange = {
                        cardsSetViewModel?.onActiveChange(it)
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
                shape = RoundedCornerShape(10.dp),
                onClick = {

                        cardsSetViewModel?.addCardsSetToFireStore(navController)

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ColorList.listColor[addCardSetUiState.color])
            ) {
                Text(text = "Erstellen", color = TextWhite, fontSize = 20.sp)
            }


        }
    }
}

@Composable
fun ColorItem(color: Color, selected: Boolean, onClick: () -> Unit) {
//    var selected by remember { mutableStateOf(false) }
    Surface(
        color = color,
        shape = CircleShape,
        modifier = Modifier
            .padding(8.dp)
            .size(30.dp)
            .noRippleClickable {
                onClick.invoke()
//                selected = !selected
            },
        elevation = 10.dp
    ) {
        if (selected){
            Icon(modifier = Modifier.size(10.dp),imageVector = Icons.Rounded.Check, contentDescription = null,  tint = TextWhite)
        }

    }

}


//@RequiresApi(Build.VERSION_CODES.R)
//@Preview
//@Composable
//fun PreviewAddMissCat() {
//    LearnProject_ComposeTheme {
//        AddMissWordCatScreen(
//            missingWordCatViewModel = null, navController = rememberNavController(
//            )
//        )
//    }
//}