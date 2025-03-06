package com.example.digikala.repository

import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.category.SubCategoryModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.CategoryApi
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApi
import javax.inject.Inject

class ProfileRepo @Inject constructor(private val api : ProfileApi): BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategoryModel> {

        return safeApiCall {
            api.getSubCategories()
        }
    }


}