package com.example.learnproject_compose.swipeableCards.peresent.cardsSet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.peresentation.addMissWordCat.components.formatData
import com.example.learnproject_compose.swipeableCards.local.model.CardsModuleSet
import com.example.learnproject_compose.ui.theme.AppTheme
import com.example.learnproject_compose.ui.theme.GreenColor
import com.example.learnproject_compose.ui.theme.Orientation
import com.example.learnproject_compose.ui.theme.TextBlack
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.WindowsSizeClass
import com.example.learnproject_compose.ui.theme.rememberWindowSizeClass

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardSetItem(
    cardSetItem: CardsModuleSet,
    color: Color?,
    isOnline: Boolean?,
    isUserAdmin: Boolean = false,
    textColor: Color = TextWhite,
    windows:WindowsSizeClass = rememberWindowSizeClass(),
    onLongClicked: () -> Unit,
    onClisck: () -> Unit
) {


            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(AppTheme.dimens.smallMedium))
                    .background(color ?: Color.Transparent)

                    .aspectRatio(
                        if (windows.height.size in 300..920 && AppTheme.orientation == Orientation.Portroit) 1.2f
                        else if (AppTheme.orientation == Orientation.Landscope && windows.height.size > 820) 3f
                        else 2f
                    )
                    .combinedClickable(
                        onLongClick = { onLongClicked.invoke() },
                        onClick = { onClisck.invoke() })
                    .fillMaxWidth().padding(10.dp)
                ,
            verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = cardSetItem.title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(9f),
                            color = textColor
                        )




                    Spacer(modifier = Modifier.width(10.dp))

                    Column() {
                        if (cardSetItem.new) {
                            Text(
                                text = "Neu", color = TextBlack,
                                fontSize = 15.sp,
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
                                tint = if (cardSetItem.active) GreenColor
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
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = cardSetItem.level,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = textColor.copy(alpha = .8f)
                    )


                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        text = cardSetItem.itemCount.toString(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h6,

                        color = TextWhite
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))



                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {

                        formatData(cardSetItem.timestamp)?.let {
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
                        cardSetItem.userName.let {
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


