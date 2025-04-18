package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.ProductDetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repo: ProductDetailsRepo) : ViewModel() {


    val productDetail = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())

     fun getProductById(productId: String) {
        viewModelScope.launch {
            productDetail.emit(repo.getProductById(productId))
        }
    }
}