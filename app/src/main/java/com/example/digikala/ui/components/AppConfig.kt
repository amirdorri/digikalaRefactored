package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.util.Constants.USER_ID
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.Constants.USER_NAME
import com.example.digikala.util.Constants.USER_PASSWORD
import com.example.digikala.util.Constants.USER_PHONE
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    getDataStoreVariables(dataStore)

    viewModel.refreshToken(USER_PHONE, USER_PASSWORD)

    LaunchedEffect(Dispatchers.Main) {
        viewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhone(user.phone)
                            dataStore.saveUserPassword(USER_PASSWORD)
                            dataStore.saveUserName(user.name ?: "null")
                            getDataStoreVariables(dataStore)

                        }

                    }
                }
                else -> {}
            }
        }
    }
}

fun getDataStoreVariables(dataStore: DataStoreViewModel) {

    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)
    USER_PHONE = dataStore.getUserPhone().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
    USER_NAME = dataStore.getUserName().toString()

}