package com.example.learnproject_compose.navigation

import DeQuizPageFalse
import QuizPage
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learnproject_compose.components.splashScreen.AnimatedSplashScreen
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
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.AddMissWordCatScreen
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.resultScreen.AddMissWordCatPage22
import com.example.learnproject_compose.screen.SearchPage
import com.example.learnproject_compose.screen.home.Home
import com.example.learnproject_compose.screen.home.HomeScreen
import com.example.learnproject_compose.screen.profile.ProfilePage
import com.example.learnproject_compose.screen.setting.SettingPage
import com.example.learnproject_compose.swipeableCards.peresent.cards.AddCardScreen
import com.example.learnproject_compose.swipeableCards.peresent.cards.SwipeableCards
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.AddCardsSetPage
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.AddCardsSetPageEdit
import com.example.learnproject_compose.swipeableCards.peresent.cardsSet.CardsSetScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = DetailsScreen.SpalshScreen.route
    ){
        composable(route = DetailsScreen.HOME2.route) { Home(navController) }
        // TODO: EinbürgerungTest Navigation
        composable(route = DetailsScreen.DeutscTest.route) { DeutsclandPage(navController) }
        composable(route = DetailsScreen.QuizDisplay.route) { QuizPage(navController) }
        composable(route = DetailsScreen.QuizDisplayCounter.route) { QuizCounterPage(navController) }
        composable(route = DetailsScreen.QuizDisplayFavorite.route) { QuizFavoriteScreen(navController) }
        composable(route = DetailsScreen.DeQuizDisplayFalse.route) { DeQuizPageFalse(navController = navController) }



        // TODO: Dictionary Navigation
        composable(route = DetailsScreen.DictionaryPages.route) { DictionaryPages(navController) }
        composable(route = DetailsScreen.DictionaryScreenSearch.route) { WordDataPage(navController = navController) }
        composable(route = DetailsScreen.DictionaryScreenFavorite.route) { SavedWordPage(navController = navController) }

        // TODO: karten navigation
        composable(route = DetailsScreen.CardsSetList.route) { CardsSetScreen(navController) }
        composable(route = DetailsScreen.AddCardItem.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = "" })
        ) { AddCardScreen(navController = navController,
                cardsId = it.arguments?.getString(MISSWORDCAT).toString()) }

        composable(
            route = DetailsScreen.SwipealCards.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { SwipeableCards(navController = navController,
                cardsSetId = it.arguments?.getString(MISSWORDCAT).toString()) }

        composable(route = DetailsScreen.AddCardsSet.route) { AddCardsSetPage(
                navController = navController,) }

        composable(route = DetailsScreen.AddCardsSetEdit.route,
            arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = "" })) { AddCardsSetPageEdit(
                navController = navController,
                cardsSetId = it.arguments?.getString(MISSWORDCAT).toString()) }


        // TODO: MissingWord Navigation

        composable(route = DetailsScreen.AddMissWordCatPreview.route) { AddMissWordCatPage22(navController) }

        composable(route = DetailsScreen.AddMissWordCat.route) { AddMissWordCatPage(
                navController = navController,) }

        composable(route = DetailsScreen.AddMissWordCatEdit.route, arguments = listOf(navArgument(MISSWORDCAT2) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { AddMissWordCatPageEdit(navController = navController,
                missCatId = it.arguments?.getString(MISSWORDCAT2).toString()) }

        composable(route = DetailsScreen.AddMissWordPage2.route, arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = "" })) { AddMissWordPage2(navController = navController,
                missCatId = it.arguments?.getString(MISSWORDCAT).toString()) }

        composable(route = DetailsScreen.MissWordPageUben.route, arguments = listOf(navArgument(MISSWORDCAT) {
                type = NavType.StringType
                defaultValue = "" })) {
            MissingOrQuizPage(
                navController = navController,
                documentId = it.arguments?.getString(MISSWORDCAT).toString()) }





        composable(route = DetailsScreen.SpalshScreen.route) { AnimatedSplashScreen(navController) }
//        detailsNavGraph(navController = navController)

    }
}