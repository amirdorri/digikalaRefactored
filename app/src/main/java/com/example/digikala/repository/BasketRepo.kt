package com.example.digikala.repository

import com.example.digikala.data.database.CartDao
import com.example.digikala.data.datastore.BaseApiResponse
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.BasketApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class BasketRepo @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItems(CartStatus.NEXT_CART)
    val currentCartItemsCount = dao.getCartItemsCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCartItemsCount(CartStatus.NEXT_CART)

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeFromCart(cart)
    }

    suspend fun deleteAllItems() {
        dao.deleteAllItems(CartStatus.CURRENT_CART)
    }

    suspend fun changeCartStatus(newCartStatus : CartStatus, id : String) {
        dao.changeStatusCart(newCartStatus, id)
    }

    suspend fun changeCartCount(newCount : Int, id : String) {
        dao.changeCountCartItem(newCount, id)
    }

}