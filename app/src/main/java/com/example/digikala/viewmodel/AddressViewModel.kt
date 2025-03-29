package com.example.digikala.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.AddressRepo
import com.example.digikala.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repo: AddressRepo) : ViewModel() {


    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())


    init {
        Log.d("VIEWMODEL_INIT", "AddressViewModel initialized, calling getUserAddressList")
        getUserAddressList(Constants.USER_TOKEN)
    }

    private fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repo.getUserAddressList(token))
            try {
                Log.d("API_CALL", "Fetching user addresses...")
                val response = repo.getUserAddressList(token)
                Log.d("API_RESPONSE", "Raw response: $response")
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch user addresses: ${e.message}")
            }
        }
    }
}