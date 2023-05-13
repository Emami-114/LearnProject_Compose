package com.example.learnproject_compose.dictionary.presentation.savedWords

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.dictionary.domain.model.Definition
import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.dictionary.util.HyperlinkText
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.backgroundWhite
import com.example.learnproject_compose.util.noRippleClickable


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SavedWordsItem(wordDataEntity: WordDataEntity, onBookmarkClicked: (WordDataEntity) -> Unit) {

    var expendetState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expendetState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable { expendetState = !expendetState }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500, easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(20.dp),
    ) {

        Column(
            modifier = Modifier
                .background(TextWhite)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(6f)) {
                    DisplayWordSaved(wordDataEntity, onBookmarkClicked)
                }
                Box(modifier = Modifier.weight(1f).noRippleClickable { expendetState != expendetState },) {

                        Icon(
                            modifier = Modifier
                                .alpha(ContentAlpha.medium)
                                .rotate(rotationState),
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )

                }
            }

            if (expendetState) {
                DisplayPhoneticSaved(wordDataEntity.phonetic)
                DisplayMeaningsSaved(wordDataEntity.meanings)
                DisplaySourceUrlsSaved(wordDataEntity.sourceUrls)

            }
        }
    }

}


@Composable
private fun DisplayWordSaved(
    wordDataEntity: WordDataEntity, onBookmarkClicked: (WordDataEntity) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordDataEntity.word, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(imageVector = Icons.Default.Bookmark,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onBookmarkClicked(wordDataEntity) })

    }
    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
private fun DisplayPhoneticSaved(phonetic: String?) {

    Text(text = phonetic ?: "")
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
private fun DisplayMeaningsSaved(meanings: List<Meaning>?) {
    meanings?.forEach { meaning ->
        DisplayPartOfSpeech(meaning.partOfSpeech)
        DisplayDefinitionsSaved(meaning.definitions)
    }
}

@Composable
private fun DisplayDefinitionsSaved(definitions: List<Definition>?) {

    definitions?.forEachIndexed { index, definition ->
        DisplayDefinitionSaved(index, definition)
    }
}

@Composable
private fun DisplayDefinitionSaved(index: Int, definition: Definition?) {
    androidx.compose.material3.Text(text = "${index + 1}. ${definition?.definition ?: ""}")
    Spacer(modifier = Modifier.height(5.dp))
    androidx.compose.material3.Text(text = "Example: ${definition?.example ?: ""}")
    Spacer(modifier = Modifier.height(8.dp))

}

@Composable
private fun DisplayPartOfSpeech(partOfSpeech: String?) {
    Text(
        text = partOfSpeech ?: "", fontSize = 17.sp, fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
}


@Composable
fun DisplaySourceUrlsSaved(sourceUrls: List<String>?) {
    androidx.compose.material3.Text(
        text = "Source:", fontSize = 15.sp, fontWeight = FontWeight.Medium
    )
    sourceUrls?.forEach { sourceUrl ->
        HyperlinkText(
            fullText = sourceUrl, hyperlinks = mapOf(
                sourceUrl to sourceUrl
            ), fontSize = 15.sp
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}


@Preview(showBackground = true)
@Composable
fun SavedWordItemPreview() {
    SavedWordsItem(wordDataEntity = WordDataEntity(
        word = "hello", phonetic = "həˈləʊ", meanings = listOf(
            Meaning(
                definitions = listOf(
                    Definition(
                        listOf("", ""),
                        definition = "used as a greeting or to begin a phone conversation.",
                        example = "hello there, Katie!",
                        synonyms = listOf("synonym1", "synonym2", "synonym3")
                    ), Definition(
                        listOf("", ""),
                        definition = "used as a greeting or to begin a phone conversation.",
                        example = "hello there, Katie!",
                        synonyms = listOf("synonym2_1", "synonym2_2", "synonyms2_3")
                    )
                ),
                partOfSpeech = "exclamation",
            )
        ), sourceUrls = listOf("https://dictionaryapi.dev/"), date = null
    ), onBookmarkClicked = {})
}