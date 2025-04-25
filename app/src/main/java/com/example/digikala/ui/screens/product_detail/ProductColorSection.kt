package com.example.digikala.ui.screens.product_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun ProductColorSection(color: List<ProductColor>) {

    Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

        Text(
            text = "رنگ : انتخاب نشده",
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        LazyRow() {
            items(color) { color ->
                ColorItem(color)
            }
        }
    }
}

@Composable
fun ColorItem(item: ProductColor) {

    Surface(
        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
        elevation = 1.dp,
        shape = MaterialTheme.roundedShape.biggerMedium
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(Color(("ff" + item.code.removePrefix("#").lowercase()).toLong(16)))
                }
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                text = item.color,
                style = MaterialTheme.typography.h6
            )
        }
    }
}