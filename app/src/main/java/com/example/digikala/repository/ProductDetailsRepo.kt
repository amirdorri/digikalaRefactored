package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProductDetailsApi
import javax.inject.Inject

class ProductDetailsRepo @Inject constructor(private val api: ProductDetailsApi) : BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
         safeApiCall {
            api.getProductById(productId)
        }

}