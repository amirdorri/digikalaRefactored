package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.category.SubCategoryModel
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {

    @GET("v1/getSubCategories")
    suspend fun getSubCategories() : Response<ResponseResult<SubCategoryModel>>
}