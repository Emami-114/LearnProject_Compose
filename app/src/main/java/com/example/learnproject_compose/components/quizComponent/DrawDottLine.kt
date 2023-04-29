package com.example.learnproject_compose.components.quizComponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.ui.theme.BlueViolet1

@Composable
fun DrawDottedLine(color: Color = BlueViolet1) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), phase = 0f)
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp), onDraw = {
        drawLine(
            color = color,
            start = Offset(0f, 0f), end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    })
}

@Preview(showBackground = true)
@Composable
fun DrawDottedLinePreview() {
    DrawDottedLine()
}