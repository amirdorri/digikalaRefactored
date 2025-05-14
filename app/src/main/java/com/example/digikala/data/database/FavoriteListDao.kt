package com.example.digikala.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.profile.FavoriteItems
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteListDao {

    @Query("SELECT * FROM FAVORITE_LIST_TABLE")
    fun getAllFavoriteItems(): Flow<List<FavoriteItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(favItem: FavoriteItems)

    @Delete
    suspend fun deleteFavoriteItem(favItem: FavoriteItems)

    @Query("DELETE FROM FAVORITE_LIST_TABLE")
    suspend fun clearFavoriteList()

    @Query("SELECT EXISTS (SELECT * FROM FAVORITE_LIST_TABLE WHERE id = :itemId)")
    fun isFavoriteItem(itemId: String): Flow<Boolean>
}