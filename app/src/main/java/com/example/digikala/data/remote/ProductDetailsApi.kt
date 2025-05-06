package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import org.w3c.dom.Comment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductDetailsApi {

    @GET("v1/getProductById")
    suspend fun getProductById(@Query("id") productId : String) : Response<ResponseResult<ProductDetail>>

    @GET("v1/getSimilarProducts")
    suspend fun getSimilarProducts(@Query("categoryId") categoryId : String) : Response<ResponseResult<List<AmazingItem>>>

    @POST("v1/setNewComment")
    suspend fun setNewComment(@Body newComment : NewComment) : Response<ResponseResult<String>>

    @GET("v1/getAllProductComments")
    suspend fun getAllProductComments(
        @Query("id") id : String,
        @Query("pageSize") pageSize : String,
        @Query("pageNumber") pageNumber : String,
        ) : Response<ResponseResult<List<ProductComment>>>


}