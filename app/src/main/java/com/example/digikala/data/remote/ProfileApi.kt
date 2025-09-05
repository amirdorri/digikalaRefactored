package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.checkout.OrderFullDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.SetUserNameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApi {

    @POST("v1/login")
    suspend fun login(@Body login : LoginRequest) : Response<ResponseResult<LoginResponse>>

    @POST("v1/setUserName")
    suspend fun setUserName(@Body userName : SetUserNameRequest) : Response<ResponseResult<String>>

    @GET("v1/getUserOrders")
    suspend fun getUserOrders(
        @Query("token") token: String
    ): Response<ResponseResult<List<OrderFullDetail>>>

}