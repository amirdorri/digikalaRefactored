package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.checkout.ConfirmPurchase
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckoutApi {

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(@Query("address") address : String) : Response<ResponseResult<Int>>

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(@Body orderRequest: OrderDetail) : Response<ResponseResult<String>>

    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(@Body confirmPurchase: ConfirmPurchase): Response<ResponseResult<String>>


}