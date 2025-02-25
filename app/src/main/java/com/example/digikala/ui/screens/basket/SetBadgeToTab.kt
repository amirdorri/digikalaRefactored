package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper

@Composable
fun SetBadgeToTab(
    selectedTabIndex: Int = 0,
    index: Int = 0,
    count: Int,
) {
    Card(
        modifier = Modifier
            .background(Color.Transparent)
    ) {
        var color = Color.Gray
        if (selectedTabIndex == index)  color = MaterialTheme.colors.digikalaRed

        Text(
            text = DigitHelper.digitBytLocateAndSeparator(count.toString()),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .background(color)
                .padding(horizontal = MaterialTheme.spacing.semiSmall)
        )


    }

}