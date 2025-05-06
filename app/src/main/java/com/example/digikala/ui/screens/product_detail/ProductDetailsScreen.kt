package com.example.digikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.model.product_detail.SliderImage
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    var productDetailList by remember { mutableStateOf(ProductDetail()) }
    var imageSlider by remember { mutableStateOf<List<SliderImage>>(emptyList()) }
    var productColors by remember { mutableStateOf<List<ProductColor>>(emptyList()) }
    var productComments by remember { mutableStateOf<List<ProductComment>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var categoryId by remember { mutableStateOf("") }
    var commentCount by remember { mutableStateOf(0) }
    var description by rememberSaveable { mutableStateOf("") }
    var technicalFeature by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    categoryId = productDetail.data.categoryId ?: ""
                    description = productDetail.data.description ?: ""
                    imageSlider = productDetail.data.imageSlider ?: emptyList()
                    productColors = productDetail.data.colors ?: emptyList()
                    technicalFeature = productDetail.data.technicalFeatures.toString()
                    productComments = productDetail.data.comments ?: emptyList()
                    commentCount = productDetail.data.commentCount ?: 0
                    productDetailList.let { Log.e("ProductDetailScreen", it.toString()) }
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

    if (loading) {
        val config = LocalConfiguration.current
        MyLoading(config.screenHeightDp.dp, true)
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(productDetailList, navController)
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(bottom = 70.dp)
            ) {
                item { ProductTopSlider(imageSlider) }
                item { ProductDetailHeader(productDetailList) }
                item { ProductColorSection(productColors) }
                item { SellerInfoSection() }
                item { SimilarProducts(categoryId) }
                item { ProductDescription(navController, description, technicalFeature) }
                item { ProductCommentSection(navController, productId,productComments,commentCount) }
                item { ProductSetComment(navController,productDetailList) }
            }
        }
    }
}
