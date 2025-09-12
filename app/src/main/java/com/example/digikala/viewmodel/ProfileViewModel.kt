package com.example.digikala.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.checkout.OrderFullDetail
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.SetUserNameRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.ProfileRepo
import com.example.digikala.ui.screens.profile.ProfileScreenState
import com.example.digikala.util.Constants
import com.example.digikala.util.Constants.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepo) : ViewModel() {

    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)
    var inputPhoneState by mutableStateOf("")
    var loadingState by mutableStateOf(false)
    var inputPasswordState by mutableStateOf("")
    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())
    val setUserNameResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())
    val orderItems = MutableStateFlow<NetworkResult<List<OrderFullDetail>>>(NetworkResult.Loading())

    fun login(){
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun refreshToken(phone : String, password : String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone, password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun setUserName(newName: SetUserNameRequest){
        viewModelScope.launch {
            setUserNameResult.emit(repository.setUserName(newName))
        }
    }

    fun getUserOrders(token : String) {
        Log.e("TOOOKEN", Constants.USER_TOKEN)
        val res = viewModelScope.launch {
            orderItems.emit(repository.getUserOrders(token))
        }
        Log.e("getUserOrders", "Result = $res")
    }

}