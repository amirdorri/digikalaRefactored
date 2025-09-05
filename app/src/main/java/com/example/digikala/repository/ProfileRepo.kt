package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.checkout.OrderFullDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.SetUserNameRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApi
import javax.inject.Inject

class ProfileRepo @Inject constructor(private val api : ProfileApi): BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> {

        return safeApiCall {
            api.login(loginRequest)
        }
    }

    suspend fun setUserName(newName: SetUserNameRequest): NetworkResult<String> {

        return safeApiCall {
            api.setUserName(newName)
        }
    }

    suspend fun getUserOrders(token: String): NetworkResult<List<OrderFullDetail>>  =
        safeApiCall {
            api.getUserOrders(token)
        }

}