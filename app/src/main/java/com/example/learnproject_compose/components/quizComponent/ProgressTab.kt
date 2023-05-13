package com.example.learnproject_compose.components.quizComponent

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.learnproject_compose.ui.theme.ButtonBlue


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

        GradientProgressbar1(10f, 20f)
    }
}


@Composable
fun GradientProgressbar1(
    score: Float,
    total: Float,
    scoreString:String = "",
    indicatorHeight: Dp = 30.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.3f),
    indicatorPadding: Dp = 24.dp,
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

//    viewModel.downloadedPercentage.observeAsState(initial = 0f)
    val animateNumber = animateFloatAsState(
        targetValue = score,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )



    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp), contentAlignment = Alignment.Center) {


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
                (animateNumber.value / total) * size.width // size.width returns the width of the canvas

            // Foreground indicator
            drawLine(
                brush = Brush.linearGradient(
                    colors = gradientColors
                ),
                cap = StrokeCap.Round,
                strokeWidth = size.height,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = progress, y = 0f)
            )

        }
        Text(
            text = scoreString,
            modifier = Modifier
                .align(Alignment.TopCenter).padding(bottom = 25.dp)
                ,
            fontWeight = FontWeight.Medium, fontSize = 15.sp
        )

    }
}





