package com.example.digikala.repository

import android.util.Log
import com.example.digikala.data.database.CartDao
import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.BasketApiInterface
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class BasketRepo @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

}