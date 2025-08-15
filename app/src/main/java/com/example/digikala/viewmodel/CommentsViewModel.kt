package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.source.ProductCommentsDataSource
import com.example.digikala.data.source.UserCommentsDataSource
import com.example.digikala.repository.CommentsRepo
import com.example.digikala.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repository: CommentsRepo) : ViewModel() {


    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    var productCommentsList: Flow<PagingData<ProductComment>> = flow { emit(PagingData.empty()) }


    var userCommentsList: Flow<PagingData<ProductComment>> = flow { emit(PagingData.empty()) }


    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }


    fun getCommentList(productId: String) {
        productCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }


    fun getUserComments() {
        userCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            UserCommentsDataSource(repository, Constants.USER_TOKEN)
        }.flow.cachedIn(viewModelScope)
    }


}
