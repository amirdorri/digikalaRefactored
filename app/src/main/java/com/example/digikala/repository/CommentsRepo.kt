package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.CommentsApi
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProductDetailsApi
import retrofit2.http.Query
import javax.inject.Inject

class CommentsRepo @Inject constructor(private val api: CommentsApi) : BaseApiResponse() {


    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }


    suspend fun getAllProductComments(
        id: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<ProductComment>> =
        safeApiCall {
            api.getAllProductComments(id, pageSize, pageNumber)
        }


    suspend fun getUserComments(
        token: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<ProductComment>> =
        safeApiCall {
            api.getUserComments(token, pageSize, pageNumber)
        }
}