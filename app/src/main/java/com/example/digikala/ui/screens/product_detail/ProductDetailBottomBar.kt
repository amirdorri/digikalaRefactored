package com.example.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.DigikalaDarktRed
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.bottomBar
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper
import com.example.digikala.util.DigitHelper.applyDiscount
import com.example.digikala.util.DigitHelper.digitBytLocateAndSeparator
import com.example.digikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailBottomBar(
    item: ProductDetail,
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }
    var itemsCountInBasket by remember { mutableStateOf(0) }
    var isShowAddToBasket by remember { mutableStateOf(true) }
    var isLaunchedEffectCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.isItemOnBasket(item._id ?: "").collectLatest {
            isShowAddToBasket = !it
            isLaunchedEffectCompleted = true
        }
    }

    LaunchedEffect(true) {
        viewModel.getBasketItemsCount(item._id ?: "").collectLatest {
            itemsCountInBasket = it
        }
    }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomBar,
        elevation = MaterialTheme.elevation.small,
        modifier = Modifier.height(70.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.biggerSmall,
                    horizontal = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                if (isLaunchedEffectCompleted && isShowAddToBasket) {
                    Button(
                        onClick = {
                            isShowAddToBasket = false
                            viewModel.insertCartItem(
                                CartItem(
                                    item._id ?: "",
                                    item.name ?: "",
                                    item.seller ?: "",
                                    item.price ?: 0,
                                    item.discountPercent ?: 0,
                                    item.imageSlider?.get(0)?.image ?: "",
                                    1,
                                    CartStatus.CURRENT_CART
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                        shape = MaterialTheme.roundedShape.small,

                        ) {
                        Text(
                            text = stringResource(R.string.add_to_basket),
                            color = Color.White,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .padding(
                                    vertical = MaterialTheme.spacing.extraSmall,
                                )
                        )
                    }
                } else if (isLaunchedEffectCompleted && !isShowAddToBasket) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colors.DigikalaDarktRed),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = DigitHelper.digitByLocate(itemsCountInBasket.toString()),
                                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                                color = Color.White
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = stringResource(R.string.in_your_basket),
                                color = Color.LightGray,
                                style = MaterialTheme.typography.h5,
                            )
                            Text(
                                text = stringResource(R.string.view_in_cart),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.Basket.route)
                                    },
                                color = MaterialTheme.colors.DigikalaDarktRed,
                                style = MaterialTheme.typography.h5,
                            )
                        }
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Row() {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically)
                            .background(
                                color = MaterialTheme.colors.DigikalaDarktRed,
                                shape = CircleShape
                            )
                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                        )
                    }

                    Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))

                    Text(
                        text = digitBytLocateAndSeparator((price).toString()),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }

                Row() {
                    Text(
                        text = digitBytLocateAndSeparator(applyDiscount(price, discountPercent).toString()),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
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