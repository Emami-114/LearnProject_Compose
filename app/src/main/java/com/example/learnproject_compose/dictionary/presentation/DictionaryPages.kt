package com.example.learnproject_compose.dictionary.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnproject_compose.dictionary.presentation.savedWords.components.TabItem
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.statusBarColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun DictionaryPages(navController: NavController) {


    val dictionaryPage =
        listOf(TabItem.Dictionary, TabItem.DictionaryFavorite)
    val pagerState = rememberPagerState(pageCount = dictionaryPage.size)
    Scaffold(topBar = {
        Tabs(tabs = dictionaryPage, pagerState = pagerState)
    }, content = {
        TabContent(tabs = dictionaryPage, pagerState = pagerState, navController)
    })
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState, navController: NavController) {

    HorizontalPager(state = pagerState, dragEnabled = false) {
        tabs[it].screen()

    }

}

@Composable
fun TopBarTest() {
    TopAppBar(
        title = { Text(text = "Dictionary", fontSize = 18.sp) },
        backgroundColor = statusBarColor,
        contentColor = TextWhite
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = statusBarColor,
        contentColor = TextWhite,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions))
        }) {
        tabs.forEachIndexed { index, bottomBarScreen ->
            LeadingIconTab(icon = {
                Icon(
                    painter = painterResource(id = bottomBarScreen.icon),
                    contentDescription = ""
                )
            }, text = {
                Text(
                    text = bottomBarScreen.title
                )
            }, selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })
        }
    }
}
