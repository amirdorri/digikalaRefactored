package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.viewmodel.DataStoreViewModel


@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel()){
    getDataBaseVariables(dataStore)
}

 fun getDataBaseVariables(dataStore: DataStoreViewModel){
    USER_LANGUAGE = dataStore.getUserLanguage()
     dataStore.saveUserLanguage(USER_LANGUAGE)
}