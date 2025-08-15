package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProductDetailsApi
import retrofit2.http.Query
import javax.inject.Inject

class ProductDetailsRepo @Inject constructor(private val api: ProductDetailsApi) : BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }

}