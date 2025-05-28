package com.example.digikala.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from shopping_cart_table where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item: CartItem)

    @Query("Delete from shopping_cart_table where cartStatus=:status")
    suspend fun deleteAllItems(status: CartStatus)

    @Query("update shopping_cart_table set count=:newCount where itemId=:id")
    suspend fun changeCountCartItem(newCount: Int, id: String)

    @Query("update shopping_cart_table set cartStatus=:newCartStatus where itemId=:id")
    suspend fun changeStatusCart(
        newCartStatus: CartStatus,
        id: String
    ) // this is for saving item to next cart

    @Query("select total(count) as count from shopping_cart_table where cartStatus=:status")
    fun getCartItemsCount(status: CartStatus): Flow<Int>

    @Query("SELECT EXISTS (SELECT * FROM shopping_cart_table where itemId = :itemId)")
    fun isItemOnBasket(itemId: String): Flow<Boolean>

    @Query("select total(count) as count from shopping_cart_table where itemId = :itemId")
    fun getBasketItemsCount(itemId: String): Flow<Int>
}