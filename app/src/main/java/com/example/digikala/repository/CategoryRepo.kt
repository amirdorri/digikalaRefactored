package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.CategoryApi
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepo @Inject constructor(private val api : CategoryApi): BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategoryModel> {
        return safeApiCall {
            api.getSubCategories()
        }
    }

    suspend fun getProductByCategory(
        categoryName: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductByCategory(categoryName, pageSize, pageNumber)
        }


    suspend fun getProductBySubCategory(
        subCategoryId: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductBySubCategory(subCategoryId, pageSize, pageNumber)
        }
}