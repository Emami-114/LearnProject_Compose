package com.example.learnproject_compose.screen.home.features

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnproject_compose.R
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.slider.SliderColor
import com.example.learnproject_compose.screen.home.slider.SliderWordModel
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.Beige1
import com.example.learnproject_compose.ui.theme.Beige2
import com.example.learnproject_compose.ui.theme.Beige3
import com.example.learnproject_compose.ui.theme.BlueViolet1
import com.example.learnproject_compose.ui.theme.BlueViolet2
import com.example.learnproject_compose.ui.theme.BlueViolet3
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen2
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.OrangeYellow1
import com.example.learnproject_compose.ui.theme.OrangeYellow2
import com.example.learnproject_compose.ui.theme.OrangeYellow3
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.ui.theme.WindowsSize
import com.example.learnproject_compose.ui.theme.WindowsSizeClass
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import kotlin.math.abs


@Composable
fun FeaturesSection(
    navController: NavController
) {

    val featuresColors = listOf<SliderColor>(
        SliderColor(
            OrangeYellow1, OrangeYellow2, OrangeYellow3
        ),
        SliderColor(
            LightGreen1, LightGreen2, LightGreen3
        ),
        SliderColor(
            BlueViolet1, BlueViolet2, BlueViolet3
        ),
        SliderColor(
            Beige1, Beige2, Beige3
        ),
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.small),

            ) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    featureTitle = "Karten Übungen",
                    featureDesc = "",
                    icon = painterResource(id = R.drawable.karte),
                    colors = featuresColors[3],
                ) {
                    navController.navigate(DetailsScreen.CardsSetList.route)
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    featureTitle = "Quiz Übungen",
                    featureDesc = "",
                    colors = featuresColors[2],
                    icon = painterResource(
                        id = R.drawable.quiz
                    )
                ) {
                    navController.navigate(DetailsScreen.AddMissWordCatPreview.route)
                }

            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.small)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    featureTitle = "Wörterbuch",
                    featureDesc = "",
                    colors = featuresColors[1],
                    icon = painterResource(
                        id = R.drawable.sprache
                    )
                ) {
                    navController.navigate(DetailsScreen.DictionaryPages.route)
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                FeaturesItem(
                    featureTitle = "Einbürgerungtest",
                    featureDesc = "",
                    featuresColors[0],
                    icon = painterResource(
                        id = R.drawable.quiztest
                    )
                ) {
                    navController.navigate(DetailsScreen.DeutscTest.route)
                }

            }

        }


    }

}

@Composable
fun FeaturesItem(
    featureTitle: String,
    featureDesc: String,
    colors: SliderColor,
    icon: Painter? = null,
    window:WindowsSizeClass = rememberWindowSizeClass(),
    onClick: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(
                if (window.height.size in 300..920 && AppTheme.orientation == Orientation.Portroit) 1f
                else if (AppTheme.orientation == Orientation.Landscope) 2f
                else 2f
            )
            .clip(RoundedCornerShape(10.dp))
            .background(colors.darkColor)
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // Medium Colored Path
        val mediumColorPath = Path().apply {
            moveTo(0f, height * 0.25f)
            quadraticBezierTo(width * -0.4999f, height * 1.0f, width * 0.0f, height * 0.5f)
            quadraticBezierTo(width * 0.57f, height * 0.99f, width * 1.4f, -height)
            lineTo(width + 100f, height + 100f)
            lineTo(-100f, height + 100f)
            close()
        }

        // Light Colored Path
        val lightColorPath = Path().apply {
            moveTo(0f, height * 0.35f)
            quadraticBezierTo(width * -0.919f, height * 0.3f, width * 0.141f, height * 0.77f)
            quadraticBezierTo(width * 0.55f, height, width * 1.45f, -height / 3f)
            lineTo(width + 100f, height + 100f)
            lineTo(-100f, height + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColorPath, color = colors.mediumColor)
            drawPath(path = lightColorPath, color = colors.lightColor)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimens.smallMedium)
        ) {
            Column {
                Text(
                    text = featureTitle,
                    style = MaterialTheme.typography.h6,
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp
                )
                Spacer(modifier = Modifier.height(AppTheme.dimens.small))
                Text(
                    text = featureDesc,
                    color = TextWhite,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium
                )
            }
            if (icon != null) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(AppTheme.dimens.large),
                    painter = icon, contentDescription = null,
                    tint = TextWhite.copy(alpha = 0.9f)
                )
            }

            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.BottomEnd),
                contentPadding = PaddingValues(AppTheme.dimens.small),
                shape = RoundedCornerShape(AppTheme.dimens.small),
                colors = ButtonDefaults.buttonColors(colors.darkColor.copy(alpha = 0.9f))
            ) {
                Text(
                    text = "Starten",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
            }
        }
    }


}


@Composable
fun SliderItem(
    word: String?,
    translate: String?,
    darkColor: Color,
    mediumColor: Color,
    lightColor: Color,
    window: WindowsSizeClass,
    onClick: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
//            .height(if (AppTheme.orientation == Orientation.Landscope) 240.dp else 200.dp)

            .aspectRatio(
                if (window.height.size in 300..620 && AppTheme.orientation == Orientation.Portroit) 3f
                else if (AppTheme.orientation == Orientation.Landscope) 6f
                else 4f
            )
            .clip(RoundedCornerShape(AppTheme.dimens.smallMedium))
            .background(darkColor)
//            .padding(7.5.dp),
                ,
        contentAlignment = Alignment.Center

    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
//        Medium Colored Path
        val mediumColorPoint1 = Offset(0f, height * 0.25f)
        val mediumColorPoint2 = Offset(width * -0.919f, height * 1.05f)
        val mediumColorPoint3 = Offset(width * 0.098f, height * 0.44f)
        val mediumColorPoint4 = Offset(width * 0.55f, height * 0.9f)
        val mediumColorPoint5 = Offset(width * 1.45f, -height.toFloat())

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
        val lightColorPoint2 = Offset(width * -0.919f, height * 1.05f)
        val lightColorPoint3 = Offset(width * 0.141f, height * 0.77f)
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
            drawPath(path = mediumColorPath, color = mediumColor)
            drawPath(path = lightColorPath, color = lightColor)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(AppTheme.dimens.large),
                painter = painterResource(id = R.drawable.sprache),
                contentDescription = null,
                tint = TextWhite.copy(alpha = 0.9f)
            )
            Column() {


                Text(
                    text = word ?: "",
                    style = MaterialTheme.typography.h5,
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                )
                Spacer(modifier = Modifier.height(AppTheme.dimens.small))
                Text(
                    text = translate ?: "",
                    color = TextWhite,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
    onClick()

}


@Preview
@Composable
fun FeaturesSinglePreview() {

//FeaturesItem(features = features[0]) {
//
//}

    SliderItem(
        word = "Hakmsackma",
        translate = "uhbjkni",
        darkColor = LightGreen3,
        mediumColor = LightGreen2,
        lightColor = LightGreen1,
        window = rememberWindowSizeClass()
    ) {

    }

}

@Preview
@Composable
fun FeaturesSinPreview() {

    FeaturesItem(
        featureTitle = "sdcascasc",
        featureDesc = "ascaecasc",
        icon = painterResource(id = R.drawable.flashkarte),
        colors = SliderColor(
            LightGreen1,
            LightGreen2, LightGreen3
        )
    ) {

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