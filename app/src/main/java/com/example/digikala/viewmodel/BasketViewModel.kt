package com.example.digikala.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.digikala.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.BasketRepo
import com.example.digikala.repository.CategoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepo) : ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val currentCartItems : Flow<List<CartItem>> = repository.currentCartItems

     fun getSuggestedItems(){
        viewModelScope.launch {
            suggestedList.emit(
                value = repository.getSuggestedItems()
            )
        }
    }
    fun insertCartItem(cart: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cart)
        }

    }

}