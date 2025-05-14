package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.profile.FavoriteItems
import com.example.digikala.repository.FavoriteListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteListViewModel @Inject constructor(private val repository: FavoriteListRepo) : ViewModel() {

    val allFavoriteItems: Flow<List<FavoriteItems>> = repository.allFavoriteItems

    fun addFavoriteItem(favItem: FavoriteItems) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavoriteItem(favItem)
        }
    }

    fun deleteFavoriteItem(favItem: FavoriteItems) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteItem(favItem)
        }
    }

    fun clearFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearFavoriteList()
        }
    }

    fun isFavoriteItem(itemId: String): Flow<Boolean> =
        repository.isFavoriteItem(itemId)

}