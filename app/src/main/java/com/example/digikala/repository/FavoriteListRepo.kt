package com.example.digikala.repository

import com.example.digikala.data.database.FavoriteListDao
import com.example.digikala.data.model.profile.FavoriteItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavoriteListRepo @Inject constructor(private val favoriteListDao: FavoriteListDao) {

    val allFavoriteItems: Flow<List<FavoriteItems>> = favoriteListDao.getAllFavoriteItems()

    suspend fun deleteFavoriteItem(favItem: FavoriteItems) {
        favoriteListDao.deleteFavoriteItem(favItem)
    }

    suspend fun addFavoriteItem(favItem: FavoriteItems) {
        favoriteListDao.addFavoriteItem(favItem)
    }

    suspend fun clearFavoriteList() {
        favoriteListDao.clearFavoriteList()
    }

    fun isFavoriteItem(itemId: String): Flow<Boolean> =
        favoriteListDao.isFavoriteItem(itemId)

}