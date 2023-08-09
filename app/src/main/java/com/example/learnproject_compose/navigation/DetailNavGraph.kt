package com.example.learnproject_compose.navigation

import DeQuizPageFalse
import QuizPage
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.learnproject_compose.deutschTest.peresentation.DeutsclandPage
import com.example.learnproject_compose.deutschTest.peresentation.quiz.QuizCounterPage
import com.example.learnproject_compose.deutschTest.peresentation.quiz.QuizFavoriteScreen
import com.example.learnproject_compose.dictionary.presentation.DictionaryPages
import com.example.learnproject_compose.dictionary.presentation.savedWords.SavedWordPage
import com.example.learnproject_compose.dictionary.presentation.wordData.WordDataPage
import com.example.learnproject_compose.missing_word.peresentation.addMissWord.AddMissWordPage2
import com.example.learnproject_compose.missing_word.peresentation.addMissWord.MissingOrQuizPage
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.AddMissWordCatPage
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.AddMissWordCatPageEdit
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen.AddMissWordCatPage22
import com.example.learnproject_compose.screen.home.Home
import com.example.learnproject_compose.swipeableCards.peresent.cards.SwipeableCards
import com.example.learnproject_compose.swipeableCards.peresent.cards.AddCardScreen
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.AddCardsSetPage
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.AddCardsSetPageEdit
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.CardsSetScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(route = Graph.DETAIL, startDestination = DetailsScreen.HOME2.route) {
        composable(route = DetailsScreen.HOME2.route) { Home(navController) }
        // TODO: EinbürgerungTest Navigation
        composable(route = DetailsScreen.DeutscTest.route) { DeutsclandPage(navController) }
        composable(route = DetailsScreen.QuizDisplay.route) { QuizPage(navController) }
        composable(route = DetailsScreen.QuizDisplayCounter.route) { QuizCounterPage(navController) }
        composable(route = DetailsScreen.QuizDisplayFavorite.route) { QuizFavoriteScreen(navController) }


        // TODO: Dictionary Navigation
        composable(route = DetailsScreen.DictionaryScreenSearch.route) { WordDataPage(navController = navController) }

        composable(route = DetailsScreen.DictionaryScreenFavorite.route) { SavedWordPage(navController = navController) }
        composable(route = DetailsScreen.DictionaryPages.route) { DictionaryPages(navController = navController) }

        composable(route = DetailsScreen.DeQuizDisplayFalse.route) { DeQuizPageFalse(navController = navController) }


        // TODO: Missing Navigation

        composable(
            route = DetailsScreen.AddMissWordCat.route) {
            AddMissWordCatPage(
                navController = navController,) }

        composable(
            route = DetailsScreen.AddMissWordCatEdit.route, arguments = listOf(navArgument(
                MISSWORDCAT2
            ) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            AddMissWordCatPageEdit(
                navController = navController,
                missCatId = it.arguments?.getString(MISSWORDCAT2).toString()

                )
        }

        composable(
            route = DetailsScreen.AddMissWordPage2.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {

            AddMissWordPage2(
                navController = navController,
                missCatId = it.arguments?.getString(MISSWORDCAT).toString()
            )

        }



        composable(route = DetailsScreen.AddMissWordCatPreview.route) {
            AddMissWordCatPage22(
                navController = navController
            )
        }



        composable(
            route = DetailsScreen.MissWordPageUben.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {

            Log.d("MYTAG", it.arguments?.getString(MISSWORDCAT).toString())
            MissingOrQuizPage(
                navController = navController,
                documentId = it.arguments?.getString(MISSWORDCAT).toString()
            )
        }


        // TODO : KarteiKarten Navigation

        composable(
            route = DetailsScreen.SwipealCards.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            SwipeableCards(
                navController = navController,
                cardsSetId = it.arguments?.getString(MISSWORDCAT).toString()

            )
        }

        composable(route = DetailsScreen.AddCardsSet.route) {
            AddCardsSetPage(
                navController = navController,
            )
        }

        composable(
            route = DetailsScreen.AddCardsSetEdit.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            AddCardsSetPageEdit(
                navController = navController,
                cardsSetId = it.arguments?.getString(MISSWORDCAT).toString()
            )
        }



        composable(route = DetailsScreen.CardsSetList.route) {
            CardsSetScreen(
                navController = navController
            )
        }

        composable(
            route = DetailsScreen.AddCardItem.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            AddCardScreen(
                navController = navController,
                cardsId = it.arguments?.getString(MISSWORDCAT).toString()
            )
        }

    }
}

const val MISSWORDCAT = "documentId"
const val MISSWORDCAT2 = "documentId2"

sealed class DetailsScreen(val route: String) {
    object HOME2 : DetailsScreen(route = "HOME2")


    // TODO: Einbürgerung Navigation

    object DeutscTest : DetailsScreen(route = "DEUTSCHTEST")

    object QuizDisplay : DetailsScreen(route = "quizPage")

    object DeQuizDisplayFalse : DetailsScreen(route = "deQuizPageFalsch")

    object QuizDisplayCounter : DetailsScreen(route = "quizPageCounter")

    object QuizDisplayFavorite : DetailsScreen(route = "quizPageFavorite")


    // TODO: Dictionary Navigation


    object DictionaryScreenSearch : DetailsScreen(route = "dictionaryScreenSearch")

    object DictionaryScreenFavorite : DetailsScreen(route = "dictionaryScreenFavorite")

    object DictionaryPages : DetailsScreen(route = "dictionaryPages")


    // TODO: Missing Navigation


    object AddMissWordCat : DetailsScreen(route = "addMissWordCat")

    object AddMissWordCatEdit : DetailsScreen(route = "addMissWordCatEdit/{$MISSWORDCAT2}") {
        fun passId3(documentId: String): String {
            return this.route.replace(
                oldValue = "{$MISSWORDCAT2}",
                newValue = documentId
            )
        }
    }

    object AddMissWordPage2 : DetailsScreen(route = "addMissWordPage2/{$MISSWORDCAT}") {
        fun passId(documentId: String): String {
            return this.route.replace(oldValue = "{$MISSWORDCAT}", newValue = documentId.toString())
        }
    }

    object AddMissWordCatPreview : DetailsScreen(route = "addMissWordCatPreview")

    object MissWordPageUben : DetailsScreen(route = "missWorduben/{$MISSWORDCAT}") {
        fun passId2(documentId: String): String {
            return this.route.replace(oldValue = "{$MISSWORDCAT}", newValue = documentId)
        }
    }

    object NotificationPage : DetailsScreen(route = "notificationPages")


// TODO: KarteiKarten Screen


    object AddCardsSet : DetailsScreen(route = "addCardsSet")

    object AddCardsSetEdit : DetailsScreen(route = "addCardsSetEdit/{$MISSWORDCAT}"){
        fun passId(documentId: String): String {
            return this.route.replace(
                oldValue = "{$MISSWORDCAT}",
                newValue = documentId
            )
        }
    }


    object CardsSetList : DetailsScreen(route = "cardsSetList")


    object AddCardItem : DetailsScreen(route = "addCardItem/{$MISSWORDCAT}") {
        fun passId(documentId: String): String {
            return this.route.replace(
                oldValue = "{$MISSWORDCAT}",
                newValue = documentId
            )
        }
    }

    object SwipealCards : DetailsScreen(route = "swipealCards/{$MISSWORDCAT}") {
        fun passId(documentId: String): String {
            return this.route.replace(
                oldValue = "{$MISSWORDCAT}",
                newValue = documentId
            )
        }
    }


    object SpalshScreen : DetailsScreen(route = "splashScreen")



}