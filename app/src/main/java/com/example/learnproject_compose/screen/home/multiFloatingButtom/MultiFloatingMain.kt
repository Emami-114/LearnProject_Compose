import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.learnproject_compose.screen.home.multiFloatingButtom.FabIcon
import com.example.learnproject_compose.screen.home.multiFloatingButtom.FabOption
import com.example.learnproject_compose.screen.home.multiFloatingButtom.MultiFabItem
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DeepBlue
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite


@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabIcon: FabIcon,

    fabOption: FabOption = FabOption(),
    onFabClicked: () -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.medium)
            ) {
                items(items.size) { index ->
                    MiniFabItem(
                        item = items[index],
                        fabOption = fabOption,
                    )
                }
                item { }
            }

        }
        FloatingActionButton(shape = RoundedCornerShape(AppTheme.dimens.smallMedium),onClick = {
            fabState.value = fabState.value.toggleValue()
            stateChanged(fabState.value)
        }, containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint,
         modifier = Modifier.size(AppTheme.dimens.large),
        ) {
            Icon(
                painter = painterResource(id = fabIcon.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .rotate(rotation)
                    .size(AppTheme.dimens.mediumLarge),
                tint = fabOption.iconTint
            )
        }

    }

}

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    fabOption: FabOption,
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (fabOption.showLabel) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.body1,
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(
                        LightGreen3,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
                    .padding(
                        horizontal = AppTheme.dimens.smallMedium,
                        vertical = AppTheme.dimens.small
                    )
            )
        }

        FloatingActionButton(shape = RoundedCornerShape(AppTheme.dimens.small),
            onClick = { item.onClick() },
            modifier = Modifier.size(AppTheme.dimens.large),
            containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            Icon(
                painter = painterResource(id = item.iconRes),
                contentDescription = null,
                tint = fabOption.iconTint,
                modifier = Modifier.size(AppTheme.dimens.mediumLarge),
            )

        }

    }

}

sealed class MultiFabState {
    object Collapsed : MultiFabState()
    object Expand : MultiFabState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() =
    remember { mutableStateOf<MultiFabState>(MultiFabState.Collapsed) }