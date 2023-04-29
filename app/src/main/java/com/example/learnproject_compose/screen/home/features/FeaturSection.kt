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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.ui.graphics.Path
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.screen.bottomBar.BottomBarScreen
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
        title = "Feature1", 10, BlueViolet1, BlueViolet2,
        BlueViolet3
    ),
    Features(
        title = "Feature2", 20, LightGreen1, LightGreen2,
        LightGreen3
    ),
    Features(
        title = "Feature3", 20, OrangeYellow1, OrangeYellow2,
        OrangeYellow3
    ),
    Features(
        title = "Feature4", 20, Beige1, Beige2,
        Beige3
    ),
    Features(
        title = "Feature5", 10, BlueViolet1, BlueViolet2,
        BlueViolet3
    ),
    Features(
        title = "Feature6", 20, LightGreen1, LightGreen2,
        LightGreen3
    ),
    Features(
        title = "Feature7", 20, OrangeYellow1, OrangeYellow2,
        OrangeYellow3
    ),
)


@Composable
fun FeaturesSection(
    navController: NavController

) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            modifier = Modifier.padding(15.dp)
        )


        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {
                    navController.navigate(BottomBarScreen.Deutsc.route)
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[2]) {}

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[1]) {}

            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[0]) {}

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(features = features[3]) {}

            }

        }


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
        val mediumColorPoint2 = Offset(width * -0.4999f, height * 1.0f)
        val mediumColorPoint3 = Offset(width * 0.0f, height * 0.5f)
        val mediumColorPoint4 = Offset(width * 0.57f, height * 0.99f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
//            standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
//        light colored path
        val lightColorPoint1 = Offset(0f, height * 0.35f)
        val lightColorPoint2 = Offset(width * -0.92f, height * 1.05f)
        val lightColorPoint3 = Offset(width * 0.15f, height * 0.70f)
        val lightColorPoint4 = Offset(width * 0.55f, height.toFloat())
        val lightColorPoint5 = Offset(width * 1.45f, -height.toFloat() / 3f)

        val lightColorPath = Path().apply {
            moveTo(lightColorPoint1.x, lightColorPoint1.y)
//            standardQuadFromTo(lightColorPoint1, lightColorPoint2)
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
//            Text(
//                text = "Start",
//                color = TextWhite,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .clip(
//                        RoundedCornerShape(10.dp)
//                    )
//                    .clickable { onClick }
//                    .align(Alignment.BottomEnd)
//                    .background(ButtonBlue.copy(alpha = 0.7f))
//                    .padding(vertical = 6.dp, horizontal = 15.dp)
//            )
            Button(onClick = onClick, modifier = Modifier
                .align(Alignment.BottomEnd), contentPadding = PaddingValues(0.dp)
                , shape = RoundedCornerShape(11.dp), colors = ButtonDefaults.buttonColors(ButtonBlue.copy(alpha = 0.7f))
            ) {
                Text(text = "Start", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

        }
    }

}

@Preview
@Composable
fun FeaturesSinglePreview() {

FeaturesItem(features = features[0]) {

}

}

@Preview(showBackground = true)
@Composable
fun FeaturesPreview() {
    FeaturesSection(rememberNavController())

}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )

}