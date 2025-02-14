package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepo) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val categories = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val centerBanners =  MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestSellerItems =  MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostVisitedItems =  MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostFavoriteItems =  MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostDiscountedItems =  MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
   // val centerBanners = MutableStateFlow<NetworkResult<List<Slide>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            launch {
                slider.emit(repository.getSlider())
            }
            launch {
                amazingItems.emit(repository.getAmazingItems())
            }
            launch {
                superMarketItems.emit(repository.getAmazingSupermarketItems())
            }
            launch {
                banners.emit(repository.getProposalBanners())
            }
            launch {
                categories.emit(repository.getCategories())
            }
            launch {
                centerBanners.emit(repository.getCenterBanners())
            }
            launch {
                bestSellerItems.emit(repository.getBestSellerItems())
            }
            launch {
                mostVisitedItems.emit(repository.getMostVisitedItems())
            }
            launch {
                mostFavoriteItems.emit(repository.getMostFavoriteProducts())
            }
            launch {
                mostDiscountedItems.emit(repository.getMostDiscountedProducts())
            }


        }
    }

//    suspend fun getSlider() {
//        viewModelScope.launch {
//           // val result =
//            slider.emit(repository.getSlider())
//        }
//    }
//
//    suspend fun getAmazingItems() {
//        viewModelScope.launch {
//            amazingItems.emit(repository.getAmazingItems())
//        }
//    }

}