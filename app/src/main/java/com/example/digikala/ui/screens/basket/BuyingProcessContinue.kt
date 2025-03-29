package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper

@Composable
fun BuyingProcessContinue(
    price: Long,
    onClick: () -> Unit
) {

    Card(
        shape = MaterialTheme.roundedShape.extraSmall,
        elevation = MaterialTheme.elevation.extraSmall,
        border = BorderStroke(color = Color.LightGray.copy(0.2f), width = 1.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.semiMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                shape = MaterialTheme.roundedShape.small
            ) {
                Text(
                    text = stringResource(R.string.purchase_process),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.biggerMedium,
                            vertical = MaterialTheme.spacing.extraSmall,
                        )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.goods_total_price),
                    color = MaterialTheme.colors.darkText,
                    style = MaterialTheme.typography.h6,
                )
                Row() {
                    Text(
                        text = DigitHelper.digitBytLocateAndSeparator(price.toString()),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
    }
}