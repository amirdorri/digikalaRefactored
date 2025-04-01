package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.remote.CheckoutApi
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApi
import javax.inject.Inject

class CheckoutRepo @Inject constructor(private val api : CheckoutApi): BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> {

        return safeApiCall {
            api.getShippingCost(address)
        }
    }

    suspend fun setNewOrder(orderDetail: OrderDetail): NetworkResult<String> {

        return safeApiCall {
            api.setNewOrder(orderDetail)
        }
    }
}