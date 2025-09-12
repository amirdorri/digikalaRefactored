package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.source.ProductByCategoryDataSource
import com.example.digikala.repository.CategoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepo) : ViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategoryModel>>(NetworkResult.Loading())

    private val _categoryId = MutableStateFlow<String?>(null)

    val productByCategoryList: Flow<PagingData<StoreProduct>> = _categoryId
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { categoryId ->
            Pager(
                PagingConfig(pageSize = 15)
            ) {
                ProductByCategoryDataSource(repository, categoryId)
            }.flow
        }
        .cachedIn(viewModelScope)

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {
            launch { subCategory.emit(repository.getSubCategories()) }
        }
    }

    fun getProductByCategory(categoryId: String) {
        _categoryId.value = categoryId
    }
}



//@HiltViewModel
//class CategoryViewModel @Inject constructor(private val repository: CategoryRepo) : ViewModel() {
//
//    val subCategory = MutableStateFlow<NetworkResult<SubCategoryModel>>(NetworkResult.Loading())
//   // var productByCategoryList: Flow<PagingData<StoreProduct>> = flow { emit(PagingData.empty()) }
//    suspend fun getAllDataFromServer() {
//        viewModelScope.launch {
//            launch { subCategory.emit(repository.getSubCategories()) }
//        }
//    }
//
//    private val _productByCategoryList =
//        MutableStateFlow<Flow<PagingData<StoreProduct>>>(flow { emit(PagingData.empty()) })
//    val productByCategoryList = _productByCategoryList.asStateFlow()
//
//    fun getProductByCategory(categoryId: String) {
//        _productByCategoryList.value = Pager(
//            PagingConfig(pageSize = 15)
//        ) {
//            ProductByCategoryDataSource(repository, categoryId)
//        }.flow.cachedIn(viewModelScope)
//    }
//
//
//}