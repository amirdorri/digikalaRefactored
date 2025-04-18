package com.example.digikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    var productDetailList by remember { mutableStateOf(ProductDetail()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    productDetailList.let { Log.e("3636", it.toString()) }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${productDetail.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }


//    Scaffold(
//        bottomBar = {
//            ProductDetailBottomBar(productDetailList , navController)
//        }
//    ) {
//        LazyColumn(){
//
//        }
//    }

    Text(text = productId)


}
