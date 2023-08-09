package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.loadingAnimation.LoadingAnimation
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWord.AddMissWordViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.MissingWordCatViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components.MissWordCatItem
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.Color2Index2
import com.example.learnproject_compose.ui.theme.Color3Index2
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.OrangeYellow3
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.backgroundWhite


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMissWordCatPage22(navController: NavController) {

    val viewMode: MissingWordCatViewModel = hiltViewModel()
    Scaffold(topBar = { TopBarSelb(title = "Zurück") {
        navController.navigateUp()
    }},modifier = Modifier.fillMaxSize()) { paddingValue ->
        AddMissWordCatScreen(
            paddingValues = paddingValue,
            missingWordCatViewModel = viewMode,
            onCardClicked = {},
            navToUben = {
//                viewMod2.getMissWordFromFireStore("A1",it)
                navController.navigate(DetailsScreen.MissWordPageUben.passId2(documentId = it))
                viewMode.updateMissingCatNew(it)

            },
            navToEdit = {
                navController.navigate(DetailsScreen.AddMissWordCatEdit.passId3(it))
            }
        )
    }

}


@Composable
private fun AddMissWordCatScreen(
    paddingValues: PaddingValues,
    missingWordCatViewModel: MissingWordCatViewModel,
    onCardClicked: (documentId: String) -> Unit,
    navToUben: (documentId: String) -> Unit,
    navToEdit: (documentId: String) -> Unit
) {

    val missWordCatUiState =
        missingWordCatViewModel.missWordCatUiState

    val userViewModel: MissingWordViewModel = hiltViewModel()
    val userNiv = userViewModel.usersUiState?.level.orEmpty()
    val isUserAdmin = userViewModel.usersUiState?.rolle == "Admin"
    var openDialog by remember { mutableStateOf(false) }
    var selectedMissWordCat: MissWordCat? by remember { mutableStateOf(null) }


    missingWordCatViewModel.getUserNivua(isUserAdmin = isUserAdmin, userNiv)



    Log.d("MYTAG", userNiv)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimens.small),

    ) {
        when (missWordCatUiState.value.missingWorCatdList) {
            is Resources2.Loading -> {

             LoadingAnimation()


            }

            is Resources2.Success -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2),contentPadding = PaddingValues(10.dp)) {
                    items(
                        missWordCatUiState.value.missingWorCatdList.data?.sortedByDescending { it.timestamp }
                            ?: emptyList()
                    ) { missWordCat ->


                        MissWordCatItem(missWordCat = missWordCat,
                            color = ColorList.listColor[missWordCat.color],
                            isOnline = missWordCat.active,
                            isUserAdmin = isUserAdmin,
                            onLongClicked = {
                                if (isUserAdmin) {
                                    openDialog = true
                                    selectedMissWordCat = missWordCat

                                }

                            }) {
                            missWordCat.documentId.let { navToUben.invoke(it) }
                            missWordCat.documentId.let { onCardClicked.invoke(it) }

                        }
                    }
                }
                AnimatedVisibility(visible = openDialog) {
                    AlertDialog(modifier = Modifier.fillMaxWidth(),
                        onDismissRequest = { openDialog = false },
                        title = {
                            Text(
                                text = "Lernset Löschen?",
                                fontSize = 20.sp,
                                color = TextBlack,
                                textAlign = TextAlign.Center
                            )
                        },
                        confirmButton = {
                            Row(modifier = Modifier.weight(8f)) {
                                Button(
                                    onClick = {
                                        selectedMissWordCat?.documentId?.let {
                                            missingWordCatViewModel.deleteMissWordCat(
                                                documentId = it
                                            )
                                        }
                                        openDialog = false
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(
                                        top = 3.dp,
                                        bottom = 3.dp,
                                        start = 10.dp,
                                        end = 10.dp
                                    ),
                                    colors = ButtonDefaults.buttonColors(containerColor = LightGreen1)
                                ) {
                                    Text(text = "Löschen", color = TextWhite)

                                }
                                Spacer(modifier = Modifier.width(9.dp))

                                Button(
                                    onClick = {
                                        selectedMissWordCat?.documentId?.let {
                                            navToEdit.invoke(it)
                                        }
                                        openDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = LightGreen3),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(
                                        top = 3.dp,
                                        bottom = 3.dp,
                                        start = 10.dp,
                                        end = 10.dp
                                    )
                                ) {
                                    Text(text = "Bearbeiten", color = TextWhite)

                                }

                            }
                        }, dismissButton = {
                            Row(modifier = Modifier.weight(2f)) {
                                Button(
                                    onClick = { openDialog = false },
                                    colors = ButtonDefaults.buttonColors(containerColor = TextWhite),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(
                                        top = 3.dp,
                                        bottom = 3.dp,
                                        start = 10.dp,
                                        end = 10.dp
                                    )
                                ) {
                                    Text(text = "Zurück", color = TextBlack)
                                }
                            }
                        }, containerColor = backgroundWhite, shape = RoundedCornerShape(10.dp)
                    )
                }
            }

            else -> {
            }
        }


    }

}


object ColorList {
    val listColor =
        listOf(
            LightGreen3, Beige3, LightGreen2, OrangeYellow3, Color2Index2, Beige2, Color3Index2,
            BlueViolet1
        )
}