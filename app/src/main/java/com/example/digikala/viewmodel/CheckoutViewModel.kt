package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.checkout.ConfirmPurchase
import com.example.digikala.data.model.checkout.OrderDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.repository.CheckoutRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepo) : ViewModel() {

    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())
    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())
    val purchaseResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getShippingCost(address : String) {
        viewModelScope.launch {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }

    fun setNewOrder(orderDetail: OrderDetail) {
        viewModelScope.launch {
            orderResponse.emit(repository.setNewOrder(orderDetail))
        }
    }
    fun confirmPurchase(confirmPurchase: ConfirmPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                purchaseResponse.emit(repository.confirmPurchase(confirmPurchase))
            }
        }
    }


}