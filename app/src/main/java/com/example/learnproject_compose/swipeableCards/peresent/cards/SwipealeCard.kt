package com.example.learnproject_compose.swipeableCards.peresent.cards

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learnproject_compose.components.quizComponent.AnimatedCounter
import com.example.learnproject_compose.navigation.DetailsScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.signIn_signUp.componente.BackIcon
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Float.max
import java.lang.Float.min
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SwipeableCards(navController: NavController, cardsSetId: String) {
    Log.d("MYTAG", "MissingWord: $cardsSetId")


    val viewModel: CardsViewModel = hiltViewModel()
    val cardUiState = viewModel.cardUiState.value
    val quizIndex = viewModel.quizIndex.value
    val question = cardUiState.data?.get(quizIndex % cardUiState.data.size)

    LaunchedEffect(Unit) {
        try {
            viewModel.getCardsFromFireStore(cardsSetId)

        } catch (e: Exception) {
            Log.d("MYTAG", e.message.toString())
        }
    }


    var index2 by remember { mutableIntStateOf(0) }


    val colors = mutableListOf(
        Color(0xff90caf9),
        Color(0xfffafafa),
        Color(0xffef9a9a),
        Color(0xFF9C27B0),
        Color(0xFF2196F3),
        Color(0xFFFFEB3B),
        Color(0xFFFF9800),
        Color(0xFF9C27B0),
        Color(0xFF009688),
        Color(0xFFF44336),

        )

//    var order by remember { mutableStateOf(listOf(0,1,2,3,4,5,6,7,8,9,10)) }
    var order by remember { mutableStateOf(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)) }
    val oerderList = order.toMutableList()

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        quizIndex?.toInt()?.let {
            AnimatedCounter(
                count = it + 1,
                bigTextColor = TextWhite,
                bigTextFontSize = 30.sp
            )
        }

        androidx.compose.material3.Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
            ) {
                append("/")

                withStyle(
                    style = SpanStyle(
                        color = TextWhite.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp,
                    )
                ) {
                    append("${cardUiState.data?.size?.toInt()}")
                }
            }
        })
    }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Zurück", color = TextWhite,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    quizIndex?.toInt()?.let {
                        AnimatedCounter(
                            count = it + 1,
                            bigTextColor = TextWhite,
                            bigTextFontSize = 30.sp
                        )
                    }

                    androidx.compose.material3.Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = TextWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                            )
                        ) {
                            append("/")

                            withStyle(
                                style = SpanStyle(
                                    color = TextWhite.copy(alpha = 0.6f),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 25.sp,
                                )
                            ) {
                                append("${cardUiState.data?.size?.toInt()}")
                            }
                        }
                    })
                }
            }
        }, navigationIcon = {
            BackIcon {
                navController.navigateUp()
            }
        },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(containerColor = DeepBlue)
        )
    }) {
        val windows = rememberWindowSizeClass()

        Box(
            Modifier
                .background(largeRadialGradient)
                .padding(vertical = 40.dp)
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            colors.forEachIndexed { idx, color ->
                var expendetState by remember { mutableStateOf(false) }
                SwipeableCard(
                    order = oerderList[idx],
                    totalCount = colors.size,
                    backgroundColor = color,
                    quiz = question?.question, answer = question?.corAnswer,
                    expandetState = expendetState,
                    onClick = { expendetState = !expendetState },
                    onSwipe = {
                        viewModel.quizIndex.value = viewModel.quizIndex.value + 1
                        expendetState = false

                        val newOrder = order.toMutableList()
                        Collections.rotate(newOrder, 1)
                        order = newOrder.toList()
                        oerderList.remove(idx)
                        colors.remove(color)

                    }
                )
            }
        }


    }

    if (quizIndex > (cardUiState.data?.size?.minus(1) ?: 0)) {
        navController.popBackStack(DetailsScreen.CardsSetList.route,inclusive = false)
    }
}

@Composable
fun SwipeableCard(
    order: Int,
    totalCount: Int,
    backgroundColor: Color = Color.White,
    quiz: String?,
    answer: String?,
    expandetState: Boolean = false,
    onClick: () -> Unit,
    onSwipe: () -> Unit
) {
    val animatedScale by animateFloatAsState(
        targetValue = 1f - order * 0.05f,
    )
    val animatedYOffset by animateDpAsState(
        targetValue = ((order + 1) * -12).dp,
    )

    Box(
        modifier = Modifier
            .zIndex((totalCount - order).toFloat())
            .offset { IntOffset(x = 0, y = animatedYOffset.roundToPx()) }
            .scale(animatedScale)

            .swipeToBack { onSwipe() }
    ) {
        SampleCard(
            backgroundColor = backgroundColor,
            quiz = quiz,
            answer = answer,
            expandetState = expandetState
        ) {
            onClick()
        }
    }
}

@Composable
fun SampleCard(
    backgroundColor: Color = Color.White,
    quiz: String? = "",
    answer: String? = "",
    expandetState: Boolean = false,
    onClick: () -> Unit
) {
    val windows = rememberWindowSizeClass()

    val rotationState by animateFloatAsState(targetValue = if (expandetState) 180f else 0f)

    Card(
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500, easing = LinearOutSlowInEasing
                )
            )
//            .height(250.dp)
            .fillMaxWidth(.8f)
            .padding(10.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            Modifier.padding(vertical = 24.dp, horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = quiz ?: "", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(20.dp))

            if (answer?.isNotEmpty() == true) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    IconButton(
                        onClick = { onClick.invoke() },
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationState),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDownward,
                            contentDescription = null,
                            tint = TextBlack
                        )
                    }
                }
                if (expandetState) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth().align(Alignment.CenterHorizontally)
                            .border(
                                .6.dp, Color.Gray,
                                shape = ShapeDefaults.Small
                            )
                            .padding(15.dp)
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = answer ?: "", style = MaterialTheme.typography.body1, modifier = Modifier.fillMaxWidth())
                    }
                }


            }


        }
    }
}


fun Modifier.pillShape() =
    this.then(
        background(Color.Black.copy(0.3f), CircleShape)
    )

@SuppressLint("ReturnFromAwaitPointerEventScope", "MultipleAwaitPointerEventScopes")
fun Modifier.swipeToBack(
    onSwipe: () -> Unit
): Modifier = composed {
    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    var leftSide by remember { mutableStateOf(true) }
    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            while (true) {
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetY.stop()
                val velocityTracker = VelocityTracker()
                awaitPointerEventScope {
                    verticalDrag(pointerId) { change ->
                        val verticalDragOffset = offsetY.value + change.positionChange().y
                        val horizontalPosition = change.previousPosition.x
                        leftSide = horizontalPosition <= size.width / 2
                        val offsetXRatioFromMiddle = if (leftSide) {
                            horizontalPosition / (size.width / 2)
                        } else {
                            (size.width - horizontalPosition) / (size.width / 2)
                        }
                        val rotationalOffset = max(1f, (1f - offsetXRatioFromMiddle) * 4f)
                        launch {
                            offsetY.snapTo(verticalDragOffset)
                            rotation.snapTo(if (leftSide) rotationalOffset else -rotationalOffset)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        change.consumePositionChange()
                    }
                }
                val velocity = velocityTracker.calculateVelocity().y
                val targetOffsetY = decay.calculateTargetValue(offsetY.value, velocity)
                if (targetOffsetY.absoluteValue <= size.height) {
                    // Not enough velocity; Reset.
                    launch { offsetY.animateTo(targetValue = 0f, initialVelocity = velocity) }
                    launch { rotation.animateTo(targetValue = 0f, initialVelocity = velocity) }
                } else {
                    // Enough velocity to fling the card to the back
                    val boomerangDuration = 600
                    val maxDistanceToFling = (size.height * 4).toFloat()
                    val maxRotations = 3
                    val EaseInOutEasing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
                    val distanceToFling = min(
                        targetOffsetY.absoluteValue + (size.height / 2), maxDistanceToFling
                    )
                    val rotationToFling = min(
                        360f * (targetOffsetY.absoluteValue / size.height).roundToInt(),
                        360f * maxRotations
                    )
                    val rotationOvershoot = rotationToFling + 12f
                    launch {
                        rotation.animateTo(targetValue = if (leftSide) rotationToFling else -rotationToFling,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                0f at 0 with EaseInOutEasing
                                (if (leftSide) rotationOvershoot else -rotationOvershoot) at boomerangDuration - 50 with LinearOutSlowInEasing
                                (if (leftSide) rotationToFling else -rotationToFling) at boomerangDuration
                            })
                        rotation.snapTo(0f)
                    }
                    launch {
                        offsetY.animateTo(targetValue = 0f,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                -distanceToFling at (boomerangDuration / 2) with EaseInOutEasing
                                40f at boomerangDuration - 70
                            })
                    }
                    delay(100)
                    onSwipe()
                }
            }
        }
    }
        .offset { IntOffset(0, offsetY.value.roundToInt()) }
        .graphicsLayer {
            transformOrigin = TransformOrigin.Center
            rotationZ = rotation.value
        }
}