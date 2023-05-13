package com.example.learnproject_compose.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.ui.theme.TextWhite

@Composable
fun Circle(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    backGroundStrockeWidth: Float = 100f,
    forgroundIndicatorColor: Color = MaterialTheme.colors.primary,
    forgroundIndicatorStrokeWidth: Float = 100f,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h3.fontSize,
    bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "300",
    smallText: String = "Ergibnes",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
) {

    // TODO: Wir Speichern unsere maxValue in State
    var allowIndicatorValue by remember { mutableStateOf(maxIndicatorValue) }
    // TODO: Unsere Logik dass unsere indicator value nicht steigt unsere macvalue
    allowIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    // TODO: hier animiert unsere wert wird ausgeführt
    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowIndicatorValue) {
        animatedIndicatorValue = allowIndicatorValue.toFloat()
    }
    // TODO: hier schreiben wir procent, also unser wert procentsatz rausfinden
    val procent = (animatedIndicatorValue / maxIndicatorValue) * 100
    // TODO: hier wird unsere
    val sweepAngele by animateFloatAsState(
        targetValue = (2.4 * procent).toFloat(),
        animationSpec = tween(1500)
    )
    // TODO: Mit diese variable animieren wir GB value langsam steigt
    val recivedValue by animateIntAsState(
        targetValue = allowIndicatorValue, animationSpec = tween(2000)
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
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backIndicatorColor,
                    indicatorStrokeWidth = backGroundStrockeWidth
                )
                forgroundIndicator(
                    componentSize = componentSize,
                    sweetAngle = sweepAngele,
                    indicatorColor = forgroundIndicatorColor,
                    indicatorStrokeWidth = forgroundIndicatorStrokeWidth
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
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Round),

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
        style = Stroke(width = indicatorStrokeWidth, cap = StrokeCap.Round),

        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),

        )

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
//    $bigTex ${bigTextSuffix.take(4)}
    Text(
        text = buildAnnotatedString {
//            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
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
                            fontSize = 30.sp
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