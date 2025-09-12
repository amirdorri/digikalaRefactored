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
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel
import com.google.accompanist.pager.rememberPagerState


@Composable
fun TabLayoutScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) { profileViewModel.getUserOrders(dataStoreViewModel.getUserToken().toString()) }

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

