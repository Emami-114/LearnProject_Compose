package com.example.learnproject_compose.components.quizComponent

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.ui.theme.AppTheme

import com.example.learnproject_compose.ui.theme.ButtonBlue
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count


@SuppressLint("UnrememberedMutableState")
@Composable
fun ProgressTab(score: Float = 5f, totalQuiz: Float = 10f) {
    var procent = (score / totalQuiz) * 100
    var progressFactory = mutableStateOf(procent * 0.0099f)

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(25.dp)
            .border(
                width = 2.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        ButtonBlue,
                        ButtonBlue
                    )
                ), shape = RoundedCornerShape(55.dp)
            )
            .clip(RoundedCornerShape(50))
            .background(Color.Transparent)
    ) {

        Button(
            onClick = { /*TODO*/ },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(progressFactory.value)
                .background(
                    brush = Brush.linearGradient(
                        listOf(ButtonBlue, ButtonBlue)
                    )
                ),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {

        }


    }

}

@Preview(showBackground = true)
@Composable
fun ProgressTabPreview() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(30.dp)) {
        ProgressTab()

        GradientProgressbar1(100f, 200f)
        GradientProgressbar2(100f, 200f, counterSting = "33")
    }
}


@Composable
fun GradientProgressbar1(
    score: Float?,
    total: Float?,
    indicatorHeight: Dp = 20.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.3f),
    indicatorPadding: Dp = 24.dp,
    bigTextColor: Color = TextBlack,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h5.fontSize,
    secoundTextFontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    gradientColors: List<Color> = listOf(
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7),
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7)
    ),
    numberStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    animationDuration: Int = 10,
    animationDelay: Int = 0
) {

   val score2 = if ((score ?: 0f) > (total ?: 0f)){
       total
    }else score

    val animateNumber = score2?.let {
        animateFloatAsState(
            targetValue = it,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDelay
            )
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = AppTheme.dimens.small)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Box(
            modifier = Modifier.weight(7f).padding(horizontal = AppTheme.dimens.small)
            ,
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 10.dp)
                    .height(indicatorHeight)
            ) {

                // Background indicator
                drawLine(
                    color = backgroundIndicatorColor,
                    cap = StrokeCap.Round,
                    strokeWidth = size.height,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f)
                )

                // Convert the downloaded percentage into progress (width of foreground indicator)
                val progress =
                    (total?.let { animateNumber?.value?.div(it) })?.times(size.width) // size.width returns the width of the canvas

                // Foreground indicator
                progress?.let { Offset(x = it, y = 0f) }?.let {
                    drawLine(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        ),
                        cap = StrokeCap.Round,
                        strokeWidth = size.height,
                        start = Offset(x = 0f, y = 0f),
                        end = it
                    )
                }

            }
        }

        Row(
            modifier = Modifier
                .weight(3f)
                .padding(bottom = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            score2?.toInt()?.let {
                AnimatedCounter(
                    count = it+1,
                    bigTextColor = bigTextColor,
                    bigTextFontSize = bigTextFontSize
                )
            }

            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = bigTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = bigTextFontSize,
                    )
                ) {
                    append("/")

                    withStyle(
                        style = SpanStyle(
                            color = bigTextColor.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Medium,
                            fontSize = secoundTextFontSize,
                        )
                    ) {
                        append("${total?.toInt()}")
                    }
                }
            })
        }


    }
}

@Composable
fun GradientProgressbar2(
    score: Float?,
    total: Float?,
    counterSting:String,
    indicatorHeight: Dp = 30.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.3f),
    indicatorPadding: Dp = 24.dp,
    bigTextColor: Color = TextBlack,
    bigTextFontSize: TextUnit = 25.sp,
    secoundTextFontSize: TextUnit = 20.sp,
    gradientColors: List<Color> = listOf(
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7),
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7)
    ),
    numberStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    animationDuration: Int = 10,
    animationDelay: Int = 0
) {

    val animateNumber = score?.let {
        animateFloatAsState(
            targetValue = it,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDelay
            )
        )
    }


    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 0.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Box(modifier = Modifier.weight(9f), contentAlignment = Alignment.Center) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(indicatorHeight)
            ) {

                // Background indicator
                drawLine(
                    color = backgroundIndicatorColor,
                    cap = StrokeCap.Round,
                    strokeWidth = size.height,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f)
                )

                // Convert the downloaded percentage into progress (width of foreground indicator)
                val progress =
                    (total?.let { animateNumber?.value?.div(it) })?.times(size.width) // size.width returns the width of the canvas

                // Foreground indicator
                progress?.let { Offset(x = it, y = 0f) }?.let {
                    drawLine(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        ),
                        cap = StrokeCap.Round,
                        strokeWidth = size.height,
                        start = Offset(x = 0f, y = 0f),
                        end = it
                    )
                }

            }
        }

        Row(
            modifier = Modifier
                .weight(4f)
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = bigTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = bigTextFontSize,
                    )
                ) {
                    append("$counterSting")


                }
            })
        }


    }
}



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: Int = 0,
    bigTextColor: Color,
    bigTextFontSize: TextUnit,

    ) {
    var oldCounter by remember { mutableStateOf(count) }

    SideEffect {
        oldCounter = count
    }

    Row() {
        val counterString = count.toString()
        val oldCounterString = oldCounter.toString()

        for (i in counterString.indices) {
            val oldChar = oldCounterString.getOrNull(i)
            val newChar = counterString[i]
            val char = if (oldChar == newChar) {
                oldCounterString[i]
            } else {
                counterString[i]
            }
            AnimatedContent(targetState = char, transitionSpec = {
                slideInVertically { it } with slideOutVertically { -it }
            }) { char ->
                Text(
                    text = "$char",
                    fontWeight = FontWeight.Bold,
                    color = bigTextColor,
                    fontSize = bigTextFontSize
                )
            }
        }

    }

}





