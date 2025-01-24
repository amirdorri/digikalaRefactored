package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET

interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems() : Response<ResponseResult<List<StoreProduct>>>


}