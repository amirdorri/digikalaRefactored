package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.example.digikala.ui.theme.font_standard
import com.example.digikala.ui.theme.spacing

@Composable
fun TermsAndRules(
    fullText: String,
    textColor: Color,
    underLinedText: List<String>,
    underLinedFontWeight: FontWeight = FontWeight.Medium,
    underLinedDecoration: TextDecoration = TextDecoration.Underline,
    fontSize: TextUnit,
    textAlign: TextAlign
) {
    Text(
        text = buildAnnotatedString {
            append(fullText)
            underLinedText.forEach { text ->
                val startIndex = fullText.indexOf(text)
                if (startIndex != -1) {  // Ensure text is found
                    addStyle(
                        style = SpanStyle(
                            fontWeight = underLinedFontWeight,
                            textDecoration = underLinedDecoration
                        ),
                        start = startIndex,
                        end = startIndex + text.length
                    )
                }
            }
        },
        textAlign = textAlign,
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.small,
            vertical = MaterialTheme.spacing.medium
        )
    )
}