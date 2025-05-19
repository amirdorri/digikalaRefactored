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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.ui.theme.CursorColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing


val selectedColorItem: MutableState<ProductColor?> = mutableStateOf(null)

@Composable
fun ProductColorSection(
    colors: List<ProductColor>,
    onSelectionChanged: (String) -> Unit = { colorName ->
        colors.forEach {
            if (it.color == colorName) {
                selectedColorItem.value = it
            }
        }
    },
) {

    Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

        Text(
            text = " رنگ: ${if (selectedColorItem.value?.color == null) "انتخاب نشده" else selectedColorItem.value?.color}",
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        var colorState by remember { mutableStateOf<List<ProductColor>>(emptyList()) }
        colorState = colors

        LazyRow() {
            items(colorState) { productColor ->
                ColorItem(
                    productColor,
                    isSelected = selectedColorItem.value?.color == productColor.color
                ) {
                    onSelectionChanged(it)
                }
            }
        }
    }
}


@Composable
fun ColorItem(
    item: ProductColor,
    isSelected: Boolean,
    onSelected: (String) -> Unit = {}
) {

    Surface(
        modifier =
        if (isSelected)
            Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .border(width = 1.dp, MaterialTheme.colors.CursorColor, CircleShape)
        else
            Modifier.padding(MaterialTheme.spacing.extraSmall),
        elevation = 1.dp,
        shape = MaterialTheme.roundedShape.biggerMedium
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelected(item.color)
                    }
                )
                .padding(MaterialTheme.spacing.small),
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