package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.ui.theme.spacing

@Composable
fun CartItemCard(
    item: CartItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.extraSmall)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = item.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

        }

    }


}