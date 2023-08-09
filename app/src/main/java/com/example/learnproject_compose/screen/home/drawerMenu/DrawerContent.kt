package com.example.learnproject_compose.screen.home.drawerMenu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learnproject_compose.R
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.navigation.BarrScreen
import com.example.learnproject_compose.screen.home.largeRadialGradient
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.LightGreen1
import com.example.learnproject_compose.ui.theme.LightGreen3
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke
import com.example.learnproject_compose.ui.theme.WindowsSizeClass
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass

@Composable
fun DrawerContent(
    navItem: List<NavigationDrawerItem>,
    userEmail: String = "",
    onClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {
        item {
            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .border(.7.dp, TextWhite, shape = CircleShape)
                    .clip(shape = CircleShape),
                painter = painterResource(
                    id = R.drawable.ic_person
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = userEmail,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = TextWhite
            )
            DrawDottedLine(color = TextWhiteDarke)
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(navItem) { item ->
            NavigationListItem(item = item)
        }

    }

}


@SuppressLint("UnrememberedMutableState")
@Composable
fun DrawerVertikalContent(
    windows: WindowsSizeClass,
    navItem: List<NavigationDrawerItem>,
    navDestination: NavDestination?,

    userEmail: String = "",
    onClick: () -> Unit
) {
    val winVer = windows.height.size < 450
    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {
        item {
            if (!winVer) {
                Image(
                    modifier = Modifier
                        .size(if (winVer) 60.dp else 100.dp)
                        .border(.7.dp, TextWhite, shape = CircleShape)
                        .clip(shape = CircleShape),
                    painter = painterResource(
                        id = R.drawable.ic_person
                    ),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                    text = userEmail,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle1,
                    color = TextWhite
                )
                DrawDottedLine(color = TextWhiteDarke)
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
        itemsIndexed(navItem) { index, item ->
            val selected by mutableStateOf(navDestination?.route == item.route)
            NavigationVertikalListItem(windows, item = item, selected = selected)
        }

    }
}

@Preview
@Composable
fun PreviewNavigationVertikalListItem() {
    NavigationVertikalListItem(windows = rememberWindowSizeClass(), item = NavigationDrawerItem(
        painterResource(id = R.drawable.mulleimer), "dewrd",
    ) {}
    )
}

@Composable
private fun NavigationVertikalListItem(
    windows: WindowsSizeClass, item: NavigationDrawerItem, selected: Boolean = true,
) {
    val winVer = windows.height.size < 450
    val unreadBubbleColor: Color = Color(0xFF0FFF93)
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(
            if (selected) LightGreen3 else Color.Transparent,
            shape = RoundedCornerShape(
           AppTheme.dimens.smallMedium
            )
        )
        .clickable { item.onClick.invoke() }
        .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box {
            Icon(
                modifier = Modifier
                    .padding(all = if (item.showUnreadBubble && item.label == "Messages") 5.dp else 2.dp)
                    .size(if (winVer) 20.dp else 40.dp),
                painter = item.image,
                contentDescription = null,
                tint = TextWhite
            )

            if (item.showUnreadBubble) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(alignment = Alignment.TopEnd)
                        .background(color = unreadBubbleColor, shape = CircleShape)
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.label,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            color = item.textColor
        )
    }
}


@Composable
private fun NavigationListItem(
    item: NavigationDrawerItem, unreadBubbleColor: Color = Color(0xFF0FFF93)
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { item.onClick.invoke() }
        .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box {
            Icon(
                modifier = Modifier
                    .padding(all = if (item.showUnreadBubble && item.label == "Messages") 5.dp else 2.dp)
                    .size(if (item.showUnreadBubble && item.label == "Messages") 24.dp else AppTheme.dimens.mediumLarge),
                painter = item.image,
                contentDescription = null,
                tint = TextWhite
            )

            if (item.showUnreadBubble) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(alignment = Alignment.TopEnd)
                        .background(color = unreadBubbleColor, shape = CircleShape)
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.label,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Medium,
            color = item.textColor
        )
    }
}


data class NavigationDrawerItem(
    val image: Painter,
    val label: String,
    val route:String = "",
    val textColor: Color = TextWhite,
    val showUnreadBubble: Boolean = false,
    val onClick: () -> Unit
)