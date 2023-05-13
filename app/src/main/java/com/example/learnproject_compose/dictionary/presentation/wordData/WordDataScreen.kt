package com.example.learnproject_compose.dictionary.presentation.wordData

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.presentation.savedWords.SavedWordsScreen
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
import com.example.learnproject_compose.screen.topbar.TopBar
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.backgroundWhite
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
fun WordDataPage(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(backgroundWhite)) {
    WordDataScreen()

    }
}

@Composable
fun WordDataScreen(viewModel: WordDataViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            SearchBar(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            WordDataList(viewModel, state.wordDataItems)
        }
        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            state.errorMessage != null -> Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                text = "Kein Ergibness!", textAlign = TextAlign.Center,
                color = Color.Red
            )
        }

    }

}

@Composable
private fun WordDataList(viewModel: WordDataViewModel, wordDataItems: List<WordData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wordDataItems.size) { index ->
            WordDataItem(wordData = wordDataItems[index], onBookMarkClicked = { clickedWord ->
                clickedWord.let { wordData ->
                    viewModel.saveOrRemoveWordData(wordData)
                }
            })
        }
    }
}

@Composable
private fun SearchBar(viewModel: WordDataViewModel) = with(viewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Search") },
        shape = RoundedCornerShape(10.dp),
        value = wordQuery.value,
        onValueChange = { wordQuery ->
            fetchWordData(wordQuery)
        },
        maxLines = 1,
        placeholder = { Text(text = "Geben Sie ein Wort ein...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },

        )
}


@Preview
@Composable
fun WordDataScreenPreview() {
    WordDataScreen()
}