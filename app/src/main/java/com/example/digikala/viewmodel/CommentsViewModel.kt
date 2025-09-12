package com.example.digikala.viewmodel

import android.util.Log
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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repository: CommentsRepo) : ViewModel() {

    val newCommentResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    // برای Product Comments
    private val _productId = MutableStateFlow<String?>(null)
    val productCommentsList: Flow<PagingData<ProductComment>> = _productId
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { productId ->
            Log.d("PagingDebug", "🔄 Creating new Pager for productId: $productId")
            Pager(
                PagingConfig(pageSize = 5)
            ) {
                ProductCommentsDataSource(repository, productId)
            }.flow
        }
        .cachedIn(viewModelScope)

    // برای User Comments
    private val _shouldLoadUserComments = MutableStateFlow(false)
    val userCommentsList: Flow<PagingData<ProductComment>> = _shouldLoadUserComments
        .filter { it } // فقط وقتی true باشه
        .distinctUntilChanged()
        .flatMapLatest {
            Log.d("PagingDebug", "🔄 Creating new Pager for user comments")
            Pager(
                PagingConfig(pageSize = 5)
            ) {
                UserCommentsDataSource(repository, Constants.USER_TOKEN)
            }.flow
        }
        .cachedIn(viewModelScope)

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }

    fun getCommentList(productId: String) {
        Log.d("PagingDebug", "📝 getCommentList called with productId: $productId")
        _productId.value = productId
    }

    fun getUserComments() {
        Log.d("PagingDebug", "👤 getUserComments called")
        _shouldLoadUserComments.value = true
    }
}