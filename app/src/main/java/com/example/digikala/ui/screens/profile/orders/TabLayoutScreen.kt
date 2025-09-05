package com.example.digikala.ui.screens.profile.orders

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.checkout.OrderFullDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.viewmodel.ProfileViewModel
import com.google.accompanist.pager.rememberPagerState


@Composable
fun TabLayoutScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    // وقتی وارد این صفحه شدیم، دیتا رو بگیر
    LaunchedEffect(Unit) {
        profileViewModel.getUserOrders()
    }

    var orderItemList by remember { mutableStateOf<List<OrderFullDetail>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    val orderItemResult by profileViewModel.orderItems.collectAsState()

    when (orderItemResult) {
        is NetworkResult.Success -> {
            loading = false
            orderItemList = orderItemResult.data ?: emptyList()
            Log.e("TabLayoutScreenSUCCESS", orderItemResult.data.toString())
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("TabLayoutScreenERROR", "")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    // PagerState
    val pagerState = rememberPagerState(
        initialPage = 0
    )

    Column {
        TabLayoutTopAppBar(navController)
        Tabs(pagerState, orderItemList)
        TabContent(pagerState, orderItemList)
    }
}

//QWEN
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun TabLayoutScreen(
//    navController: NavHostController,
//    profileViewModel: ProfileViewModel = hiltViewModel() // دریافت خودکار از Hilt
//) {
//
//    val orderResult by profileViewModel.orderItems.collectAsState()
//    val orderList by remember {
//        derivedStateOf {
//            when (orderResult) {
//                is NetworkResult.Success -> orderResult.data ?: emptyList()
//                else -> emptyList()
//            }
//        }
//    }
//
//
//    val pagerState = rememberPagerState(5)
//
//    Column(modifier = Modifier.fillMaxSize()) {
//
//        // AppBar
//        TabLayoutTopAppBar(navController)
//
//        // تب‌ها
//        Tabs(pagerState = pagerState, orders = orderList)
//
//        // محتوای هر تب
//
//            TabContent(pagerState = pagerState, orders = orderList)
//
//    }
//}





//@Composable
//fun TabLayoutScreen(
//    navController: NavHostController,
//    orders: String,
//    profileViewModel: ProfileViewModel = hiltViewModel()
//) {
//    val pagerState = rememberPagerState()
//
//    LaunchedEffect(Unit) {
//        profileViewModel.getUserOrders()
//    }
//    val orderList9 = remember { mutableStateOf<List<OrderFullDetail>>(emptyList()) }
//
//    val orderItemResult by profileViewModel.orderItems.collectAsState()
//
//    when (orderItemResult) {
//        is NetworkResult.Success -> {
//            val orderList = orderItemResult.data ?: emptyList()
//
//            if (orderList.isNotEmpty()) {
//                Column {
//                    Text(orderList[0].orderUserName)
//                    Text(orderList[0].orderAddress)
//                    Text(orderList[0].orderUserPhone)
//
//                    // اگر بخواهید همه سفارش‌ها را نمایش دهید:
//                    // LazyColumn { items(orderList) { item -> ... } }
//                }
//            } else {
//                Text("شما هنوز سفارشی ثبت نکرده‌اید.")
//            }
//        }
//
//        is NetworkResult.Error -> {
//            Text("خطا: ${orderItemResult.message}")
//        }
//
//        is NetworkResult.Loading -> {
//            CircularProgressIndicator() // یا Text("در حال بارگذاری...")
//        }
//    }
//
//    Column {
//        TabLayoutTopAppBar(navController)
//        Tabs(orders = orderList9, pagerState = pagerState)
//        TabContent(pagerState)
//    }
//}
