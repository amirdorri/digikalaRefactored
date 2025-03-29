package com.example.digikala.repository

import android.util.Log
import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.remote.AddressApi
import com.example.digikala.data.remote.CheckoutApi
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApi
import javax.inject.Inject

class AddressRepo @Inject constructor(private val api: AddressApi) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> {
        Log.d("REPO_CALL", "Calling API with token: $token")
        return safeApiCall {
            val response = api.getUserAddressList(token)
            Log.d("REPO_RESPONSE", "Raw response: $response")
            response
        }
    }
}