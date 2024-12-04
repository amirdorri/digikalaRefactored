package com.example.digikala.viewmodel

import android.transition.Slide
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val categories = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val centerBanners =  MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
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