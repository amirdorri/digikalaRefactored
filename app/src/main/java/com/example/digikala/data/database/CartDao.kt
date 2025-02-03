package com.example.digikala.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart : CartItem)

    @Query("select * from shopping_cart_table where cartStatus=:status")
     fun getAllItems(status: CartStatus) : Flow<List<CartItem>>


}