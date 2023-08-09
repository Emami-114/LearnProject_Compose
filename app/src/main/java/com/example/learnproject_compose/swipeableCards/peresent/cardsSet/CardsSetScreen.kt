package com.example.learnproject_compose.swipeableCards.peresent.cardsSet

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.loadingAnimation.LoadingAnimation
import com.example.learnproject_compose.components.topBar.TopBarSelb
import com.example.learnproject_compose.deutschTest.domain.model.Resources2
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.MissingWordCatViewModel
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components.MissWordCatItem
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen.ColorList
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LearnProject_ComposeTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.backgroundWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsSetScreen(navController: NavController) {

    val viewMode: CardsSetViewModel = hiltViewModel()
    Scaffold(topBar = { TopBarSelb(title = "Karteikarten") {
        navController.navigate(DetailsScreen.HOME2.route)
    }},
        modifier = Modifier.fillMaxSize()) { paddingValue ->
        CardsSetView(
            paddingValues = paddingValue,
            cardsSetViewModel = viewMode,
            onCardClicked = {},
            navToUben = {
                navController.navigate(DetailsScreen.SwipealCards.passId(it))
                viewMode.updateCardsSetNew(it)

            },
            navToEdit = {
                navController.navigate(DetailsScreen.AddCardsSetEdit.passId(it))
            }
        )
    }

}


@Composable
private fun CardsSetView(
    paddingValues: PaddingValues,
    cardsSetViewModel: CardsSetViewModel?,
    onCardClicked: (documentId: String) -> Unit,
    navToUben: (documentId: String) -> Unit,
    navToEdit: (documentId: String) -> Unit
) {

    val cardsSetUiState =
        cardsSetViewModel?.cardsSetUiState

    val userViewModel: MissingWordViewModel = hiltViewModel()
    val userNiv = userViewModel.usersUiState?.level.orEmpty()
    val isUserAdmin = userViewModel.usersUiState?.rolle == "Admin"
    var openDialog by remember { mutableStateOf(false) }
    var selectedCardSet: CardsModuleSet? by remember { mutableStateOf(null) }


    cardsSetViewModel?.getCardsSetUserNivua(isUserAdmin = isUserAdmin, userNiv)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
            .padding(paddingValues)
            .padding(AppTheme.dimens.small)
        ,

        ) {
        when (cardsSetUiState) {
            is Resources2.Loading -> {

             LoadingAnimation()


            }

            is Resources2.Success -> {
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)) {
                    items(
                        cardsSetUiState.data?.sortedByDescending { it.timestamp }
                            ?: emptyList()
                    ) { cardSet ->


                        CardSetItem(cardSetItem = cardSet,
                            color = ColorList.listColor[cardSet.color],
                            isOnline = cardSet.active,
                            isUserAdmin = isUserAdmin,
                            onLongClicked = {
                                if (isUserAdmin) {
                                    openDialog = true
                                    selectedCardSet = cardSet

                                }

                            }) {
                            cardSet.documentId.let { navToUben.invoke(it) }
                            cardSet.documentId.let { onCardClicked.invoke(it) }

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
                                        selectedCardSet?.documentId?.let {
                                            cardsSetViewModel.deleteMissWordCat(
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
                                        selectedCardSet?.documentId?.let {
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


@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun PreviewCardSet() {
        CardsSetView(
            paddingValues = PaddingValues(5.dp),
            cardsSetViewModel = null,
            onCardClicked = {},
            navToUben = {},
            navToEdit = {}
        )

}