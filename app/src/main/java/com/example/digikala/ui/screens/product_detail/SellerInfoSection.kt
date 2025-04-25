package com.example.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.screens.basket.DetailRow
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaLightGreen
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.unSelecedBottomBar
import com.example.digikala.util.DigitHelper

@Composable
fun SellerInfoSection() {
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp)
    )
    Column(
        modifier = Modifier
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.medium,
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Text(
            text = stringResource(id = R.string.seller),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_logo),
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {

                Text(
                    text = stringResource(id = R.string.digikala),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${DigitHelper.digitByLocate("101")}%" +
                                " رضایت خریداران | عملکرد ",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.semiDarkColor,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.semiMedium,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.guarantee),
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.small)
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {

                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.productWarranty),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )

                Spacer(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }

        }

        Row(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.biggerSmall)) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(vertical = MaterialTheme.spacing.small)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.in_stock),
                    contentDescription = "",
                    tint = MaterialTheme.colors.DarkCyan,
                    modifier = Modifier.size(16.dp),
                )

                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    tint = MaterialTheme.colors.DarkCyan,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                )

                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    tint = MaterialTheme.colors.DarkCyan,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                )
            }

            Column(Modifier.padding(horizontal = MaterialTheme.spacing.biggerMedium)) {

                Text(
                    text = stringResource(id = R.string.in_digi_stock),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.semiDarkColor,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                )

                DetailRow(
                    painterResource(id = R.drawable.k1),
                    stringResource(id = R.string.digikala_send),
                    color = MaterialTheme.colors.DigikalaLightRed,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

                DetailRow(
                    painterResource(id = R.drawable.k2),
                    stringResource(id = R.string.fast_send),
                    color = MaterialTheme.colors.DigikalaLightGreen,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

            }
        }


        Spacer(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        Row(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {

                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.digiclub_get_score),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.darkText,
                )
            }
        }

        Spacer(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.info),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {

                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = "${stringResource(id = R.string.manufacturer_price)} 111" +
                            " ${stringResource(id = R.string.toman)}",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.unSelecedBottomBar,
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                ),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.better_price_suggestion),
                style = MaterialTheme.typography.extraSmall,
                color = MaterialTheme.colors.unSelecedBottomBar,
            )

            Image(
                painter = painterResource(id = R.drawable.mark),
                modifier = Modifier
                    .size(25.dp), contentDescription = ""
            )
        }
    }
}
