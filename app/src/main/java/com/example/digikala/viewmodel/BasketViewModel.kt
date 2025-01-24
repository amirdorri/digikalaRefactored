package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.BasketRepo
import com.example.digikala.repository.CategoryRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepo) : ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

     fun getSuggestedItems(){
        viewModelScope.launch {
            suggestedList.emit(
                value = repository.getSuggestedItems()
            )
        }
    }

}