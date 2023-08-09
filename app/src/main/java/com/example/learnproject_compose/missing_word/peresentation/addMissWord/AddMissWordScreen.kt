package com.example.learnproject_compose.missing_word.peresentation.addMissWord

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.MissingWordCatViewModel
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMissWordPage2(navController: NavController, missCatId: String) {
    val addMissWordViewModel: AddMissWordViewModel = hiltViewModel()



    Log.d(
        "MYTAG",
        "MissWordItem : ${addMissWordViewModel.missWordUiState.value?.missingWordList?.data}"
    )

    Scaffold(
        bottomBar = {},
        topBar = {
            TopBarSelb(
                title = "Zurück",
                onNavigate = { navController.navigate(DetailsScreen.HOME2.route) })
        }) {
        Column(modifier = Modifier.padding(it)) {

            AddMissWordScreen2(
                addmissingWordViewModel = addMissWordViewModel,
                missCatId = missCatId
            ) {
                navController.navigate(DetailsScreen.HOME2.route)
            }
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
private fun AddMissWordScreen2(
    addmissingWordViewModel: AddMissWordViewModel?,
    missCatId: String,
    onNavigate: () -> Unit
) {

    val context = LocalContext.current
    val missWordCategory: MissingWordCatViewModel = hiltViewModel()

    Log.d("MYTAG", "AddWordScreen : $missCatId")

    val addMissUiState = addmissingWordViewModel?.addMissWordUiState ?: AddMissUiState()


    val isMissCatIdNotBlank = missCatId.isNotEmpty()

    LaunchedEffect(key1 = Unit) {
        if (isMissCatIdNotBlank) {
//            missingWordCatViewModel?.getMissWordCatSingle(missCatId)
        } else {
            addmissingWordViewModel?.resetMissState()
        }
    }

    val scope = rememberCoroutineScope()
    val scafolState = rememberScaffoldState()

    Scaffold(scaffoldState = scafolState) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient).padding(AppTheme.dimens.smallMedium)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (addMissUiState.addStatus) {
                scope.launch {
//                    scafolState.snackbarHostState.showSnackbar("Erfolgreich hinzugefügt.")
                    Toast.makeText(context, "Erfolgreich hinzugefügt.", Toast.LENGTH_SHORT).show()

                    addmissingWordViewModel?.resetMissState()
//                    onNavigate.invoke()

                }
            }
            if (addMissUiState.updateMissWordCatStatus) {
                scope.launch {
                    addmissingWordViewModel?.resetMissState()

                }
            }

            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
                    .padding(end = 13.dp),
                fontSize = 16.sp,
                text = "Breite erstellte Fragen:" +
                        " ${(addmissingWordViewModel?.missWordUiState?.value?.missingWordList?.data)?.count() ?: 0}",
                color = TextWhite

            )



            OutlinedTextField(
                value = addMissUiState.question,
                onValueChange = { addmissingWordViewModel?.onQuestionChange(it) },
                label = {
                    Text(
                        text = "Frage",
                        style = androidx.compose.material.MaterialTheme.typography.subtitle2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(110.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGreen1,
                    focusedLabelColor = LightGreen1,
                    unfocusedBorderColor = TextWhiteDarke,
                    unfocusedLabelColor = TextWhiteDarke,
                    textColor = TextWhite,
                    cursorColor = LightGreen1
                )
            )

            OutlinedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(
                    1.dp,
                    TextWhiteDarke
                ),
                onClick = { addmissingWordViewModel?.onSpacerChange(" ________ ") }) {
                Text(text = " ___________ ", color = TextWhite)

            }
            OutlinedTextField(
                value = addMissUiState.corAnswer,
                onValueChange = { addmissingWordViewModel?.onCorAnswerChange(it) },
                label = {
                    Text(
                        text = "Richtige Antwort",
                        style = androidx.compose.material.MaterialTheme.typography.subtitle2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                value = addMissUiState.answer1,
                onValueChange = { addmissingWordViewModel?.onAnswer1Change(it) },
                label = {
                    Text(
                        text = "1.Antwort",
                        style = androidx.compose.material.MaterialTheme.typography.subtitle2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                value = addMissUiState.answer2,
                onValueChange = { addmissingWordViewModel?.onAnswer2Change(it) },
                label = {
                    Text(
                        text = "2.Antwort",
                        style = androidx.compose.material.MaterialTheme.typography.subtitle2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                value = addMissUiState.answer3,
                onValueChange = { addmissingWordViewModel?.onAnswer3Change(it) },
                label = {
                    Text(
                        text = "3.Antwort",
                        style = androidx.compose.material.MaterialTheme.typography.subtitle2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGreen1,
                    focusedLabelColor = LightGreen1,
                    unfocusedBorderColor = TextWhiteDarke,
                    unfocusedLabelColor = TextWhiteDarke,
                    textColor = TextWhite,
                    cursorColor = LightGreen1
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                        .weight(1f),
                    shape = RoundedCornerShape(AppTheme.dimens.small),
                    border = BorderStroke(1.dp, color = LightGreen3),
                    onClick = { onNavigate.invoke() }) {
                    Text(
                        text = "Zurück", color = TextWhite, style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                    .weight(1f),
                    shape = RoundedCornerShape(AppTheme.dimens.small),
                    colors = ButtonDefaults.buttonColors(
                        LightGreen3
                    ),
                    onClick = {
                        addmissingWordViewModel?.addMissToFireStore(
                            documen = missCatId,
                            collection = missCatId
                        )
                        addmissingWordViewModel?.getMissWordFromFireStore(missCatId)


                        // TODO: Hiere wird LernSetItem wird Updated
                        missWordCategory.updateMissingCatItemCount(
                            documentId = missCatId,
                            itemCount = (addmissingWordViewModel?.missWordUiState?.value?.missingWordList?.data?.plus(
                                1
                            ))?.count()
                                ?: 0
                        )


                    }
                ) {

                    Text(
                        text = "Speichern",
                        color = TextWhite,
                        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }


            }

            Spacer(modifier = Modifier.height(70.dp))
        }

    }


}


//@RequiresApi(Build.VERSION_CODES.R)
@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun PreviewAddMissCat() {
    AddMissWordScreen2(addmissingWordViewModel = null, missCatId = "") {}

}