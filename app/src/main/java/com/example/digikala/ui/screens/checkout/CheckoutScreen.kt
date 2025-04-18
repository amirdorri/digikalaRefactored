package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.screens.basket.BuyingProcessContinue
import com.example.digikala.ui.screens.basket.CartPriceDetailSection
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.BasketViewModel
import com.example.digikala.viewmodel.CheckoutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//@Composable
//fun CheckoutScreen(
//    navController: NavHostController,
//    basketViewModel: BasketViewModel = hiltViewModel(),
//    checkoutViewModel: CheckoutViewModel = hiltViewModel()
//) {
//
//    val cartDetail by basketViewModel.cartDetail.collectAsState()
//    val userCartItems by basketViewModel.userCartItems.collectAsState()
//    var shippingCost by remember { mutableStateOf(0) }
//    var loading by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
//
//    when (shippingCostResult) {
//        is NetworkResult.Success -> {
//            loading = false
//            shippingCost = shippingCostResult.data ?: 0
//            Log.e("checkoutScreen", shippingCostResult.data.toString())
//        }
//
//        is NetworkResult.Loading -> {
//            loading = true
//            Log.e("checkoutScreen", "LOADING...")
//        }
//
//        is NetworkResult.Error -> {
//            loading = false
//            Log.e("checkoutScreen", shippingCostResult.message.toString())
//        }
//    }
//
//    val modalBottomSheetState = rememberModalBottomSheetState(
//        ModalBottomSheetValue.Hidden,
//        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
//        skipHalfExpanded = false
//    )
//    ModalBottomSheetLayout(
//        sheetState = modalBottomSheetState,
//        sheetContent = { DeliveryTimeBottomSheet() }
//        ) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            LazyColumn {
//                item { CheckoutTopBar(navController) }
//                item {
//                    CartAddressSection(navController) { address ->
//                       // Log.e("dorrri", address[0].address)
//                        if (address.isNotEmpty()) {
//                            checkoutViewModel.getShippingCost("سیستان و بلوجستان")//address[0].address
//                        } else
//                            checkoutViewModel.getShippingCost("")
//                    }
//                }
//                item {
//                    CartItemReviewSection(cartDetail, userCartItems) {
//                        coroutineScope.launch {
//                            if (modalBottomSheetState.isVisible)
//                                modalBottomSheetState.hide()
//                            else
//                                modalBottomSheetState.show()
//                        }
//                        // modalBottomSheetState != modalBottomSheetState
//                    }
//                }
//                item { CartInfoSection() }
//                item { CartPriceDetailSection(cartDetail, shippingCost = shippingCost) }
//
//            }
//
//            if (loading)
//                MyLoading(65.dp, true)
//            else {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.BottomCenter)
//                ) {
//                    BuyingProcessContinue(
//                        price = cartDetail.payablePrice,
//                        shippingCost = shippingCost,
//                        onClick = {}
//                    )
//
//                }
//            }
//        }
//    }
//}

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val cartDetail by basketViewModel.cartDetail.collectAsState()
    val currentCartItems by basketViewModel.userCartItems.collectAsState()
    var address by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }
    var addressPhone by remember { mutableStateOf("") }
    var shippingCost by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()

    LaunchedEffect(true) {
        if (address.isNotBlank())
            checkoutViewModel.getShippingCost(address)
        else
            checkoutViewModel.getShippingCost("")
    }

    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen error : ${shippingCostResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    var orderId by remember { mutableStateOf("") }
    // val orderResult by checkoutViewModel.orderResponse.collectAsState()

    LaunchedEffect(Dispatchers.Main) {
        checkoutViewModel.orderResponse.collectLatest { orderResult ->
            when (orderResult) {
                is NetworkResult.Success -> {
                    orderId = orderResult.data ?: ""
                    navController.navigate(Screen.ConfirmPurchase.withArgs(orderId, cartDetail.payablePrice + shippingCost))
                }

                is NetworkResult.Error -> {
                    //   loading = false
                    Log.e("3636", "CheckoutScreen error : ${orderResult.message}")
                }

                is NetworkResult.Loading -> {
                    //  loading = true
                }
            }
        }
    }


    val modalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = { DeliveryTimeBottomSheet() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn() {
                item { CheckoutTopBar(navController) }
                item {
                    CartAddressSection(navController) { addressList ->
                        if (addressList.isNotEmpty()) {
                            address = addressList[0].address
                            addressName = addressList[0].name
                            addressPhone = addressList[0].phone
                        }
                    }
                }
                item {
                    CartItemReviewSection(cartDetail, currentCartItems) {
                        coroutineScope.launch {
                            if (modalBottomSheetState.isVisible)
                                modalBottomSheetState.hide()
                            else
                                modalBottomSheetState.show()
                        }
                        // modalBottomSheetState != modalBottomSheetState
                    }
                }
                item { CartInfoSection() }
                item { CartPriceDetailSection(cartDetail, shippingCost) }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                BuyingProcessContinue(cartDetail.payablePrice, shippingCost) {
                    checkoutViewModel.setNewOrder(
                        OrderDetail(
                            orderAddress = address,
                            orderProducts = currentCartItems,
                            orderTotalDiscount = cartDetail.totalDiscount,
                            orderTotalPrice = cartDetail.payablePrice + shippingCost,
                            orderUserPhone = addressPhone,
                            orderUserName = addressName,
                            token = USER_TOKEN
                        )
                    )
                }
            }
        }
    }
}
