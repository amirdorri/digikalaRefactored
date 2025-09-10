package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {

    @GET("v1/getSubCategories")
    suspend fun getSubCategories() : Response<ResponseResult<SubCategoryModel>>

    @GET("v1/getProductByCategory")
    suspend fun getProductByCategory(
        @Query("categoryName") categoryName: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<StoreProduct>>>



    @GET("v1/getProductBySubCategory")
    suspend fun getProductBySubCategory(
        @Query("subCategoryId") subCategoryId: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<StoreProduct>>>

}