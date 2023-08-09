package com.example.learnproject_compose.components

import android.icu.text.ListFormatter.Width
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.ui.theme.TextWhite

@Composable
fun Circle(
    canvasSize: Dp = 300.dp,
    totalIndicatorValue: Int = 0,
    correctIndicatorValue: Int = 0,
    failureIndicatorValue: Int = 0,

    maxIndicatorValue: Int = 100,
    backIndicatorColor: Color = Color.Black.copy(alpha = 0.2f),
    backGroundStrockeWidth: Float = 100f,
    forgroundIndicatorColor: Color = MaterialTheme.colors.primary,
    forgroundIndicatorColor2: Color = MaterialTheme.colors.secondary,
    forgroundIndicatorColor3: Color = Color.Red,
    forgroundIndicatorStrokeWidth: Float = 100f,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h5.fontSize,
    bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "300",
    smallText: String = "Ergibnes",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
) {

    // TODO: Wir Speichern unsere maxValue in State
    var allowIndicatorValue by remember { mutableStateOf(maxIndicatorValue) }
    // TODO: Unsere Logik dass unsere indicator value nicht steigt unsere macvalue
    allowIndicatorValue = if (totalIndicatorValue <= maxIndicatorValue) {
        totalIndicatorValue
    } else {
        maxIndicatorValue
    }

    // TODO: Wir Speichern unsere maxValue in State
    var allowIndicatorValue2 by remember { mutableStateOf(maxIndicatorValue) }
    // TODO: Unsere Logik dass unsere indicator value nicht steigt unsere macvalue
    allowIndicatorValue2 = if (correctIndicatorValue <= maxIndicatorValue) {
        correctIndicatorValue
    } else {
        maxIndicatorValue
    }

    // TODO: Wir Speichern unsere maxValue in State
    var allowIndicatorValue3 by remember { mutableStateOf(maxIndicatorValue) }
    // TODO: Unsere Logik dass unsere indicator value nicht steigt unsere macvalue
    allowIndicatorValue3 = if (failureIndicatorValue <= maxIndicatorValue) {
        failureIndicatorValue
    } else {
        maxIndicatorValue
    }

    // TODO: hier animiert unsere wert wird ausgeführt
    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowIndicatorValue) {
        animatedIndicatorValue = allowIndicatorValue.toFloat()
    }

    // TODO: hier animiert unsere wert wird ausgeführt
    var animatedIndicatorValue2 by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowIndicatorValue2) {
        animatedIndicatorValue2 = allowIndicatorValue2.toFloat()
    }

    // TODO: hier animiert unsere wert wird ausgeführt
    var animatedIndicatorValue3 by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowIndicatorValue3) {
        animatedIndicatorValue3 = allowIndicatorValue3.toFloat()
    }

    // TODO: hier schreiben wir procent, also unser wert procentsatz rausfinden
    val coreProcent = (animatedIndicatorValue2 / maxIndicatorValue) * 100
    val totalProcent = (animatedIndicatorValue / maxIndicatorValue) * 100
    val failedProcent = (animatedIndicatorValue3 / maxIndicatorValue) * 100
    // TODO: hier wird unsere
    val sweepAngele by animateFloatAsState(
        targetValue = (2.4 * totalProcent).toFloat(),
        animationSpec = tween(1000)
    )

    val sweepAngele2 by animateFloatAsState(
        targetValue = (2.4 * coreProcent).toFloat(),
        animationSpec = tween(2000)
    )
    val sweepAngele3 by animateFloatAsState(
        targetValue = (2.4 * failedProcent).toFloat(),
        animationSpec = tween(3000)
    )

    // TODO: Mit diese variable animieren wir GB value langsam steigt
    val recivedValue by animateIntAsState(
        targetValue = allowIndicatorValue, animationSpec = tween(2000)
    )

    val recivedValue2 by animateIntAsState(
        targetValue = allowIndicatorValue2, animationSpec = tween(2000)
    )
    val recivedValue3 by animateIntAsState(
        targetValue = allowIndicatorValue3, animationSpec = tween(2000)
    )

// TODO: mit diese variable sagen wir wenn auf 0 den wert ist, soll die farbe gray
    val animatedBigText by animateColorAsState(
        targetValue = if (allowIndicatorValue == 0)
            TextWhite.copy(alpha = 0.5f)
        else bigTextColor,
        animationSpec = tween(1500)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                val componentSize2 = size / 1.31f
                val componentSize3 = size / 1.19f

                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backIndicatorColor,
                    indicatorStrokeWidth = backGroundStrockeWidth
                )
                forgroundIndicator(
                    componentSize = componentSize,
                    sweetAngle = sweepAngele,
                    indicatorColor = forgroundIndicatorColor,
                    indicatorStrokeWidth = backGroundStrockeWidth
                )
//                backgroundIndicator(
//                    componentSize = componentSize2,
//                    indicatorColor = backIndicatorColor,
//                    indicatorStrokeWidth = 35f
//                )
                forgroundIndicator(
                    componentSize = componentSize,
                    sweetAngle = sweepAngele2,
                    indicatorColor = forgroundIndicatorColor2,
                    indicatorStrokeWidth = 60f
                )

                forgroundIndicator(
                    componentSize = componentSize,
                    sweetAngle = sweepAngele3,
                    indicatorColor = forgroundIndicatorColor3,
                    indicatorStrokeWidth = 20f
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbededElements(
            bigTex = recivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigText,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize
        )
        Spacer(modifier = Modifier.height(10.dp))
        EmbededElements2(
            bigTex = recivedValue2,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigText,
            bigColorSuffix = forgroundIndicatorColor2

        )
        EmbededElements2(
            bigTex = recivedValue3,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigText,
            bigColorSuffix = forgroundIndicatorColor3

            )

    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Square),

        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),

        )
}

fun DrawScope.forgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    sweetAngle: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweetAngle,
        useCenter = false,
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Square),

        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
    )

}

@Composable
fun EmbededElements2(
    bigTex: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigColorSuffix: Color = Color.Red,

    ) {
        Row(modifier = Modifier.fillMaxWidth(0.3f),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Card(modifier = Modifier.size(20.dp),backgroundColor = bigColorSuffix) {
            }
            Text(text = "$bigTex", fontSize = bigTextFontSize, color = bigTextColor, fontWeight = FontWeight.Medium)
        }


}

@Composable
fun EmbededElements(
    bigTex: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit,
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = bigTextColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = bigTextFontSize
                )
            ) {
                append("$bigTex/")
                withStyle(
                    style = SpanStyle(
                        color = TextWhite.copy(alpha = .8f),
                        fontWeight = FontWeight.Medium,
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                ) {
                    append("$bigTextSuffix")
                }
            }
//            }
        },
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}


@Preview(showBackground = true)
@Composable
fun CirClePreview() {
    Circle()
}