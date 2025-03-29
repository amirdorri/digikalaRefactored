package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.ui.screens.basket.CartPriceDetailSection
import com.example.digikala.viewmodel.BasketViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val cartDetail by viewModel.cartDetail.collectAsState()
    val userCartItems by viewModel.userCartItems.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn() {
            item { CheckoutTopBar(navController) }
            item { CartAddressSection(navController) }
            item { CartItemReviewSection("145700", cartDetail, userCartItems) }
            item { CartInfoSection() }
            item { CartPriceDetailSection(cartDetail) }

        }
    }
}