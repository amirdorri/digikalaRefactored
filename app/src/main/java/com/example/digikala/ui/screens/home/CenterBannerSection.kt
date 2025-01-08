package com.example.digikala.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.CenterBannerItem
import com.example.digikala.viewmodel.HomeViewModel

@Composable
fun CenterBannerSection(
    bannerNumber : Int,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bannerItemList by remember { mutableStateOf<List<Slider>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    val bannerItemResult by viewModel.centerBanners.collectAsState()

    when (bannerItemResult) {
        is NetworkResult.Success -> {
            bannerItemList = bannerItemResult.data ?: emptyList()
            loading = false
            // Log.e("dorri", bannersList.toString())
        }

        is NetworkResult.Error -> {
            loading = false
            //Log.e("dorri1", cardResult.toString())
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (bannerItemList.isNotEmpty()){

        CenterBannerItem(imgUrl = bannerItemList[bannerNumber - 1].image)

    }

}