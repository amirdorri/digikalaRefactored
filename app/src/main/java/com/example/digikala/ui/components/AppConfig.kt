package com.example.digikala.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.util.Constants.USER_ID
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.Constants.USER_PASSWORD
import com.example.digikala.util.Constants.USER_PHONE
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel


@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    getDataBaseVariables(dataStore)
    viewModel.refreshToken(USER_PHONE, USER_PASSWORD)
    val loginResponse by viewModel.loginResponse.collectAsState()

    when (loginResponse) {

        is NetworkResult.Success -> {
            loginResponse.data?.let { user ->
                if (user.token.isNotEmpty()) {
                    dataStore.saveUserToken(user.token)
                    dataStore.saveUserId(user.id)
                    dataStore.saveUserPhone(user.phone)
                    dataStore.saveUserPassword(USER_PASSWORD)
                    getDataBaseVariables(dataStore)
                    Log.e("dorrri", "REFRESH TOKENNN!!!!")
                }
            }
        }

        else -> {}
    }
}

fun getDataBaseVariables(dataStore: DataStoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)
    USER_PHONE = dataStore.getUserPhone().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
}