package com.example.learnproject_compose.screen.home.slider

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnproject_compose.R
import com.example.learnproject_compose.screen.home.features.SliderItem
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlideUi(wordList: SliderWordModel, sliderColor: SliderColor) {

val windows = rememberWindowSizeClass()
//    val wordList = viewModel.wordsList.data ?: listOf()


    val pagerState = rememberPagerState(initialPage = 0, pageCount = 1)
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2500)
            pagerState.animateScrollToPage(
                animationSpec = tween(durationMillis = 900),
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(if (windows.height.size < 821)300.dp else 500.dp),
                ,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Wort des Tages",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            modifier = Modifier.padding(start = 5.dp)
                .padding(AppTheme.dimens.smallMedium)
        )


//        Spacer(modifier = Modifier.height(10.dp))
        HorizontalPager(
            state = pagerState, modifier = Modifier
//                .weight(1f)
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(AppTheme.dimens.smallMedium)) {
//                val newSlidColor = sliderColor[page]

//                val newWord = wordList?.get(page) ?: SliderWordModel()

                SliderItem(
                    word = wordList.word,
                    translate = wordList.translate,
                    sliderColor.darkColor,
                    sliderColor.mediumColor,
                    sliderColor.lightColor,
                    window = windows
                ) {}


//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//
//                    }

//                    Column(
//                        modifier = Modifier
//                            .align(Alignment.BottomStart)
//                            .padding(10.dp)
//                    ) {
//
//
//                        Text(
//                            text = newSlid.title,
//                            style = MaterialTheme.typography.titleMedium,
//                            color = TextWhite,
//                            fontWeight = FontWeight.ExtraBold,
//                            fontSize = 23.sp
//
//                        )
//                        Text(
//                            text = newSlid.desc,
//                            style = MaterialTheme.typography.bodySmall,
//                            color = TextWhite,
//                            fontWeight = FontWeight.Normal,
//                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
//                        )
//
//                    }


            }

        }
//        HorizontalPagerIndicator(
//            pagerState = pagerState,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(8.dp),
//            indicatorShape = CutCornerShape(10.dp),
//        )


    }

}

@Preview(showBackground = true)
@Composable
fun SlideUiPreview() {
    SlideUi(
        wordList = SliderWordModel("", "dcww", "dcwcwcwc", "A1"), sliderColor = SliderColor(
            LightGreen1, LightGreen2, LightGreen3
        )
    )

}


val slideList = listOf(
    SliderModel(
        "Slide1",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet",
        R.drawable.image1
    ), SliderModel(
        "Slide2",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
        R.drawable.image2,
    ), SliderModel(
        "Slide3",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
        R.drawable.image3,
    ), SliderModel(
        "Slide4",
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
        R.drawable.image4,
    )

)