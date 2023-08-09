package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.DeepBlue2
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.WindowsSizeClass
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MissWordCatItem(
    missWordCat: MissWordCat,
    color: Color?,
    isOnline: Boolean?,
    isUserAdmin: Boolean = false,
    textColor: Color = TextWhite,
    onLongClicked: () -> Unit,
    onClisck: () -> Unit
) {

    var onOrOffText = remember { mutableStateOf(isOnline) }


    color?.let {
        CardDefaults.cardColors(
             containerColor = it
        )
    }?.let {


        Card(
            colors = it,
            modifier = Modifier
                .padding(8.dp)
                .combinedClickable(
                    onLongClick = { onLongClicked.invoke() },
                    onClick = { onClisck.invoke() })
                .fillMaxWidth().height(140.dp)

        ) {


            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
//                    .weight(8f)
            ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row() {
                        Text(
                            text = missWordCat.title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(9f),
                            color = textColor
                        )


                    Text(
                        text = missWordCat.itemCount.toString(),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis,
                        color = TextWhite
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column() {
                        if (missWordCat.new) {
                            Text(
                                text = "Neu", color = TextBlack,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .align(Alignment.End)
                                    .background(GreenColor)
                                    .padding(2.dp)
                                    .padding(start = 3.dp, end = 3.dp),
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        if (isUserAdmin) {
                            Icon(
                                imageVector = Icons.Filled.Circle,
                                contentDescription = null,
                                tint = if (missWordCat.active) GreenColor
                                else Color.Red.copy(alpha = .8f),
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.End)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
//                    if (!missWordCat.description.isNullOrEmpty()){
                        Text(
                            text = missWordCat.description,
                            fontSize = 15.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            color = textColor.copy(alpha = .8f)
                        )

                    }
                    missWordCat.level?.let {
                        Text(
                            text = it,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(4.dp),
                            maxLines = 1,
                            color = textColor.copy(alpha = .5f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {

                            formatData(missWordCat.timestamp)?.let {
                                Text(
                                    text = it,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(4.dp),
                                    maxLines = 1,
                                    color = textColor.copy(alpha = .5f),
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            missWordCat.userName?.let {
                                Text(
                                    text = it,
                                    color = textColor.copy(alpha = .5f),
                                    style = MaterialTheme.typography.subtitle1,

                                    )
                            }

                        }


                }
            }

        }
    }


}

@SuppressLint("SuspiciousIndentation")
fun formatData(timestamp: Timestamp?): String? {
    val sdf = SimpleDateFormat("dd.MM.yy", Locale.getDefault())

    return timestamp?.toDate()?.let { sdf.format(it) }

}


@Preview
@Composable
fun PreviewMissWordCat() {
    MissWordCatItem(
        MissWordCat(
            "",
            title = "Hallo",
            description = "GVubjhbkjkn",
            level = "A3",
            active = true,
            timestamp = Timestamp.now(),
            documentId = "",
            userName = "Emami",
        ), color = Color.Gray, onLongClicked = {}, isUserAdmin = true, isOnline = true
    ) {}
}