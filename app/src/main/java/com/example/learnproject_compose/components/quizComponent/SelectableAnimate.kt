package com.example.learnproject_compose.components.quizComponent


import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.model.QuizModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectableAnimate(
    modifier: Modifier = Modifier,
    selected: Boolean,
    quizAnswer: String,
    isActiveState: Boolean,
    titleColor: Color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(
        alpha = 0.2f
    ),
    titleSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleWight: FontWeight = FontWeight.Medium,
    borderWidth: Dp = 1.dp,
    borderColor: Color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(
        alpha = 0.2f
    ),
    borderShape: Shape = RoundedCornerShape(size = 10.dp),
    icon: ImageVector = Icons.Default.CheckCircle,
    iconColor: Color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(
        alpha = 0.2f
    ),
    onClick: () -> Unit
) {
    val scaleA = remember { Animatable(1f) }
    val scaleB = remember { Animatable(1f) }


    LaunchedEffect(key1 = selected) {
        if (selected) {
            launch {
                scaleA.animateTo(targetValue = 0.3f, animationSpec = tween(50))
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }

            launch {
                scaleB.animateTo(targetValue = 0.9f, animationSpec = tween(50))
                scaleB.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
        }

    }

    Column(
        modifier = Modifier
            .scale(scale = scaleB.value)
            .border(width = borderWidth, color = borderColor, shape = borderShape)
            .clip(borderShape)
            .clickable(!isActiveState) { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = quizAnswer,
                modifier = Modifier.weight(8f),
                style = TextStyle(
                    color = titleColor, fontSize = titleSize, fontWeight = titleWight
                ),
            )
            IconButton(
                enabled = false,
                onClick = { (onClick()) }, modifier = Modifier
                    .weight(2f)
                    .scale(scaleA.value)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Check",
                    tint = iconColor
                )

            }

        }


    }


}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun SelectablePreview() {
//    SelectableAnimate(
//        selected = true,
//        title = "Das ist Erste Frage ?",
//    ) {}
}