package com.example.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.BasketViewModel
import com.example.digikala.viewmodel.DataStoreViewModel

@Composable
fun ShoppingCart(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel(),
) {
    val cartDetail = viewModel.cartDetail.collectAsState()
    val currentCartItemState: BasketScreenState<List<CartItem>> by
    viewModel.currentCartItems.collectAsState(BasketScreenState.Loading)

    var isCartEmpty by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 60.dp),
        ) {

            item {
                if (USER_TOKEN == "null")
                    LoginOrRegisterDialog(navController)
            }

            when (currentCartItemState) {
                is BasketScreenState.Success -> {
                    if ((currentCartItemState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                        isCartEmpty = true
                        item { EmptyBasket() }
                        item { SuggestListSection() }
                    } else {
                        isCartEmpty = false
                        items((currentCartItemState as BasketScreenState.Success<List<CartItem>>).data) { item ->
                            CartItemCard(item, CartStatus.CURRENT_CART)
                        }
                        item {
                            CartPriceDetailSection(cartDetail.value)
                        }
                    }
                }

                is BasketScreenState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .height(LocalConfiguration.current.screenHeightDp.dp - 60.dp)
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.small),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.please_wait),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.darkText,
                            )
                        }
                    }
                }

                is BasketScreenState.Error -> {
                    Log.e("dorri","ERROR")
                }
            }
        }

        if (!isCartEmpty) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp)
            ) {
                BuyingProcessContinue(cartDetail.value.payablePrice) {
                    if (USER_TOKEN == "null") {
                        navController.navigate(Screen.Profile.route)
                    } else {
                        navController.navigate(Screen.Checkout.route)
                    }
                }
            }
        }
    }
}