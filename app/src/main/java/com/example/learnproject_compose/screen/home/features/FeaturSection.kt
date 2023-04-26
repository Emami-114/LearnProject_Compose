package com.example.learnproject_compose.screen.home.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.ui.theme.AquaBlue
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet2
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.DeepBlue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope

import androidx.compose.ui.graphics.Path
import com.example.learnproject_compose.ui.theme.Beige1
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.ButtonBlue
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.OrangeYellow1
import com.example.learnproject_compose.ui.theme.OrangeYellow2
import com.example.learnproject_compose.ui.theme.OrangeYellow3

import com.example.learnproject_compose.ui.theme.TextWhite
import kotlin.math.abs

val features: List<Features> = listOf(
    Features(
        title = "Einbürgerung Test", 10, BlueViolet1, BlueViolet2,
        BlueViolet3
    ),
    Features(
        title = "Einbürgerung Test", 20, LightGreen1, LightGreen2,
        LightGreen3
    ),
    Features(
        title = "Einbürgerung Test", 20, OrangeYellow1, OrangeYellow2,
        OrangeYellow3
    ),
    Features(
        title = "Einbürgerung Test", 20, Beige1, Beige2,
        Beige3
    ),
    Features(
        title = "Einbürgerung Test", 10, BlueViolet1, BlueViolet2,
        BlueViolet3
    ),
    Features(
        title = "Einbürgerung Test", 20, LightGreen1, LightGreen2,
        LightGreen3
    ),
    Features(
        title = "Einbürgerung Test", 20, OrangeYellow1, OrangeYellow2,
        OrangeYellow3
    ),
)


@Composable
fun FeaturesSection(

) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            modifier = Modifier.padding(15.dp)
        )
//        Row(modifier = Modifier.fillMaxWidth()) {
//           FeaturesItem(features = features[0])
//           FeaturesItem(features = features[1])
//        }
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
//            modifier = Modifier.fillMaxHeight()
//        ) {
//            items(features.size) {
//                FeaturesItem(features = features[it])
//            }
//        }

    }

}


@Composable
fun FeaturesItem(features: Features, onClick: () -> Unit) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
//        Medium Colored Path
        val mediumColorPoint1 = Offset(0f, height * 0.25f)
        val mediumColorPoint2 = Offset(width * 0.0f, height * 0.95f)
        val mediumColorPoint3 = Offset(width * 0.11f, height * 0.15f)
        val mediumColorPoint4 = Offset(width * 0.55f, height * 1f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
//        light colored path
        val lightColorPoint1 = Offset(0f, height * 0.35f)
        val lightColorPoint2 = Offset(width * 0.1f, height * 0.75f)
        val lightColorPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightColorPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightColorPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColorPath = Path().apply {
            moveTo(lightColorPoint1.x, lightColorPoint1.y)
            standardQuadFromTo(lightColorPoint1, lightColorPoint2)
            standardQuadFromTo(lightColorPoint2, lightColorPoint3)
            standardQuadFromTo(lightColorPoint3, lightColorPoint4)
            standardQuadFromTo(lightColorPoint4, lightColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColorPath, color = features.mediumColor)
            drawPath(path = lightColorPath, color = features.lightColor)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Column() {

                Text(
                    text = features.title,
                    fontSize = 20.sp,
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                )
                Text(
                    text = "${features.count} leassons",
                    color = TextWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

            }
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .clickable { onClick }
                    .align(Alignment.BottomEnd)
                    .background(ButtonBlue.copy(alpha = 0.7f))
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun FeaturesPreview() {
    FeaturesSection()
}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )

}