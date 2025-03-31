package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.screens.basket.BuyingProcessContinue
import com.example.digikala.ui.screens.basket.CartPriceDetailSection
import com.example.digikala.viewmodel.BasketViewModel
import com.example.digikala.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    val cartDetail by basketViewModel.cartDetail.collectAsState()
    val userCartItems by basketViewModel.userCartItems.collectAsState()
    var shippingCost by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()

    when(shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }
        is NetworkResult.Loading -> {
            loading = true
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("checkoutScreen", shippingCostResult.message.toString())
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn() {
            item { CheckoutTopBar(navController) }
            item { CartAddressSection(navController) { address ->
                if (address.isNotEmpty()){
                    checkoutViewModel.getShippingCost(address[0].address)
                } else
                    checkoutViewModel.getShippingCost("")
            } }
            item { CartItemReviewSection(shippingCost, cartDetail, userCartItems) }
            item { CartInfoSection() }
            item { CartPriceDetailSection(cartDetail, shippingCost = shippingCost) }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BuyingProcessContinue(price = cartDetail.payablePrice, shippingCost = shippingCost, onClick = {})

        }
    }
}