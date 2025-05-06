package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.source.ProductCommentsDataSource
import com.example.digikala.repository.ProductDetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repo: ProductDetailsRepo) : ViewModel() {


    val productDetail = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())
    val similarProducts = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val newCommentResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

     fun getProductById(productId: String) {
        viewModelScope.launch {
            productDetail.emit(repo.getProductById(productId))
        }
    }
    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProducts.emit(repo.getSimilarProducts(categoryId))
        }
    }
     fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repo.setNewComment(newComment))
        }
    }

    var commentsList: Flow<PagingData<ProductComment>> = flow { emit(PagingData.empty()) }

    fun getCommentList(productId: String) {
        commentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsDataSource(repo, productId)
        }.flow.cachedIn(viewModelScope)
    }
}