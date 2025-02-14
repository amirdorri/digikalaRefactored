package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CategoryRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepo) : ViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategoryModel>>(NetworkResult.Loading())


    suspend fun getAllDataFromServer() {
        viewModelScope.launch {
            //fire and forget
            launch {
                subCategory.emit(repository.getSubCategories())
            }
        }
    }

}