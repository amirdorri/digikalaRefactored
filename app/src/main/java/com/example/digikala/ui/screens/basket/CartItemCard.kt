package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaLightGreen
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.veryExtraSmall
import com.example.digikala.util.DigitHelper
import com.example.digikala.viewmodel.BasketViewModel

@Composable
fun CartItemCard(
    item: CartItem,
    mode: CartStatus,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val count = remember { mutableIntStateOf(item.count) }
    val discountAmount = (item.price * item.discountPercent) / 100

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.extraSmall)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Column {
                    Text(
                        text = stringResource(R.string.your_shopping_list),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.darkText
                    )
                    Text(
                        text = "${DigitHelper.digitBytLocateAndSeparator(count.intValue.toString())} کالا ",
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray
                    )

                }

                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = MaterialTheme.colors.darkText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "cart item Photo",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(.3f),
                )

                Column(
                    modifier = Modifier
                        .weight(.7f)
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall)
                    )
                    DetailRow(
                        icon = painterResource(R.drawable.warranty),
                        text = stringResource(R.string.warranty),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )

                    DetailRow(
                        icon = painterResource(R.drawable.store),
                        text = stringResource(R.string.digikala),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )

                    Row(

                    ) {
                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(vertical = MaterialTheme.spacing.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )

                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )

                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )

                        }
                        Column(Modifier.padding(horizontal = 8.dp)) {

                            Text(
                                text = item.seller,
                                style = MaterialTheme.typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.semiDarkColor,
                                modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k1),
                                stringResource(id = R.string.digikala_send),
                                color = MaterialTheme.colors.DigikalaLightRed,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k2),
                                stringResource(id = R.string.fast_send),
                                color = MaterialTheme.colors.DigikalaLightGreen,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )

                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier
                        .clip(MaterialTheme.roundedShape.semiSmall)
                        .border(
                            1.dp,
                            color = Color.LightGray.copy(alpha = 0.6f),
                            MaterialTheme.roundedShape.semiSmall
                        )
                ) {

                    if (mode == CartStatus.CURRENT_CART) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.small,
                                    vertical = MaterialTheme.spacing.extraSmall
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_increase_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier
                                    .clickable {
                                        count.intValue++
                                        viewModel.changeItemCount(
                                            id = item.itemId,
                                            newCount = count.intValue
                                        )
                                    }
                            )

                            Text(
                                text = DigitHelper.digitBytLocateAndSeparator(count.intValue.toString()),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier.padding(MaterialTheme.spacing.medium)
                            )
                            if (count.intValue == 1)
                                Icon(
                                    painter = painterResource(R.drawable.digi_trash),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable { viewModel.removeCartItem(item) }
                                )
                            else
                                Icon(
                                    painter = painterResource(R.drawable.ic_decrease_24),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable {
                                        count.intValue--
                                        viewModel.changeItemCount(
                                            id = item.itemId,
                                            newCount = count.intValue
                                        )
                                    }
                                )


                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = 48.dp,
                                    vertical = MaterialTheme.spacing.small
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        viewModel.changeCartItemStatus(
                                            item.itemId,
                                            CartStatus.CURRENT_CART
                                        )
                                    }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))


                Column {

                    Text(
                        text = "${DigitHelper.digitBytLocateAndSeparator(discountAmount.toString())} ${
                            stringResource(
                                R.string.discount
                            )
                        }",
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.digikalaRed
                    )

                    Row() {

                        Text(
                            text = DigitHelper.digitBytLocateAndSeparator(item.price.toString()),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )

                        Icon(
                            painter = painterResource(R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(MaterialTheme.spacing.extraSmall)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            if (mode == CartStatus.CURRENT_CART) {
                Row(
                    modifier = Modifier
                        .clickable {
                            viewModel.changeCartItemStatus(
                                id = item.itemId,
                                newStatus = CartStatus.NEXT_CART
                            )
                        }
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(R.string.save_to_next_list),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DarkCyan,

                        )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan
                    )
                }
            } else {

                Row(
                    modifier = Modifier
                        .clickable { viewModel.removeCartItem(item) }
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(R.string.delete_from_list),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DigikalaLightRed,

                        )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DigikalaLightRed
                    )
                }
            }
        }
    }
}

@Composable
fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fontStyle: TextStyle = TextStyle()
) {
    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(16.dp),
            tint = color,
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = text,
            style = fontStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.semiDarkColor,

            )

    }
}

