package com.example.digikala.ui.screens.category

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.digikala.ui.screens.home.SearchBarSection
import com.example.digikala.viewmodel.CategoryViewModel


@Composable
fun SubCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavHostController,
    categoryId: String
) {

    LaunchedEffect(categoryId) {
        Log.d("SubCategoryScreen", "Calling getProductByCategory with $categoryId")
        viewModel.getProductByCategory(categoryId)
    }

    // اگر از راه حل ۱ یا ۲ استفاده می‌کنید:
    val productList = viewModel.productByCategoryList.collectAsLazyPagingItems()

    // یا اگر می‌خواهید همان کد قدیمی را نگه دارید:
    // val pagingFlow by viewModel.productByCategoryList.collectAsState()
    // val productList = pagingFlow.collectAsLazyPagingItems()

    // اضافه کردن debug برای بررسی وضعیت
    Log.d("SubCategoryScreen", "LoadState: ${productList.loadState}")
    Log.d("SubCategoryScreen", "ItemCount: ${productList.itemCount}")

    Text(
        text = categoryId,
        color = Color.White, // استفاده از Color.White بهتر است
        fontSize = 22.sp
    )

    Column {
        SearchBarSection()

        when (val refreshState = productList.loadState.refresh) {
            is LoadState.Loading -> {
                // نمایش loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                // نمایش پیام خطا
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "خطا در بارگذاری: ${refreshState.error.localizedMessage}",
                        color = Color.Red
                    )
                }
            }
            is LoadState.NotLoading -> {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(
                        count = productList.itemCount,
                        key = { index ->
                            val product = productList[index]
                            if (product != null) {
                                "${product._id}_$index" // ترکیب id و index برای منحصر به فرد بودن
                            } else {
                                "loading_$index"
                            }
                        },
                        contentType = productList.itemContentType { "product" }
                    ) { index ->
                        val product = productList[index]
                        if (product != null) {
                            HorizontalProductCard(product, navController)
                        }
                    }
                }
            }
        }
    }
}
//@Composable
//fun SubCategoryScreen(
//    viewModel: CategoryViewModel = hiltViewModel(),
//    navController: NavHostController,
//    categoryId: String
//) {
//
//    LaunchedEffect(categoryId) {
//        Log.d("SubCategoryScreen", "Calling getProductByCategory with $categoryId")
//        viewModel.getProductByCategory(categoryId)
//    }
//
//    val pagingFlow by viewModel.productByCategoryList.collectAsState()
//    val productList = pagingFlow.collectAsLazyPagingItems()
//
//    // val productList = viewModel.productByCategoryList.collectAsLazyPagingItems()
//
//
//    Log.e("dorrri2", "${viewModel.getProductByCategory(categoryId)} ////// ${productList.loadState}  ${productList.itemCount}")
//    Text(
//        text = categoryId,
//        color = White,
//        fontSize = 22.sp
//    )
//    Column {
//        SearchBarSection()
//        LazyColumn(Modifier.fillMaxSize() ){
//            //paging3
//            items(
//                count = productList.itemCount,
//                key = productList.itemKey{ product -> product._id },
//                contentType = productList.itemContentType{"product"}
//            ){ index ->
//                HorizontalProductCard(productList[index]!! , navController)
//            }
//        }
//    }
//}
