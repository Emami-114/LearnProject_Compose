package com.example.learnproject_compose.dictionary.presentation.wordData

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnproject_compose.components.quizComponent.DrawDottedLine
import com.example.learnproject_compose.dictionary.domain.model.Definition
import com.example.learnproject_compose.dictionary.domain.model.Meaning
import com.example.learnproject_compose.dictionary.domain.model.WordData
import com.example.learnproject_compose.dictionary.util.HyperlinkText
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhite
import com.example.learnproject_compose.ui.theme.TextWhiteDarke

@Composable
fun WordDataItem(wordData: WordData, onBookMarkClicked: (WordData) -> Unit) {
    Column {
        DisplayWord(wordData, onBookMarkClicked)

        DisplayPhonetic(wordData.phonetic)
        DrawDottedLine()

        DisplayMeanings(wordData.meanings)
        DrawDottedLine()

        DisplaySourceUrls(wordData.sourceUrls)
    }

}

@Composable
private fun DisplaySourceUrls(sourceUrls: List<String>?) {
    Text(text = "Source:", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = TextWhiteDarke)
    sourceUrls?.forEach { sourceUrl ->
        HyperlinkText(
            fullText = sourceUrl, hyperlinks = mapOf(
                sourceUrl to sourceUrl
            ), fontSize = 15.sp
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
private fun DisplayMeanings(meanings: List<Meaning>?) {
    meanings?.forEach { meaning ->
        DisplayPartOfSpeech(meaning.partOfSpeech)
        DisplayDefinitions(meaning.definitions)
    }
}

@Composable
private fun DisplayDefinitions(definitions: List<Definition>?) {
    definitions?.forEachIndexed { index, definition ->
        DisplayDefinition(index, definition)
    }
}

@Composable
private fun DisplayDefinition(index: Int, definition: Definition?) {
    Text(text = "${index + 1}. ${definition?.definition ?: ""}", color = TextWhite)
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = "Example: ${definition?.example ?: ""}", color = TextWhite)
    Spacer(modifier = Modifier.height(8.dp))

}

@Composable
private fun DisplayPartOfSpeech(partOfSpeech: String?) {
    Text(
        text = partOfSpeech ?: "",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = TextWhite
    )
    Spacer(modifier = Modifier.height(5.dp))

}

@Composable
private fun DisplayPhonetic(phonetic: String?) {
    Text(text = phonetic ?: "", color = TextWhite)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun DisplayWord(wordData: WordData, onBookMarkClicked: (WordData) -> Unit) {
    var isSaved by remember { mutableStateOf(wordData.isSaved) }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordData.word,
            fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = TextWhite
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "isSaved Icon",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    isSaved = !isSaved
                    wordData.isSaved = !wordData.isSaved
                    onBookMarkClicked(wordData)
                }, tint = TextWhite
        )

    }
}

@Preview
@Composable
fun WordDataItemPreview() {
    WordDataItem(
        wordData = WordData(
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
            ), sourceUrls = listOf("https://dictionaryapi.dev/")
        ),
    ) {}
}