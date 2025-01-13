package com.example.digikala.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.category.Sub
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.viewmodel.CategoryViewmodel

@Composable
fun SubCategorySection(viewModel: CategoryViewmodel = hiltViewModel()) {

    var loading by remember { mutableStateOf(false) }
    val subCategoryResult by viewModel.subCategory.collectAsState()

    var toolList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var digitalList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var mobileList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var supermarketList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var fashionList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var nativeList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var toyList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var beautyList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var homeList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var bookList by remember { mutableStateOf <List<Sub>>(emptyList()) }
    var sportList by remember { mutableStateOf <List<Sub>>(emptyList()) }

    when(subCategoryResult) {

        is NetworkResult.Success -> {
            loading = false
           // toolList = subCategoryResult.data?.tool ?: emptyList()
            subCategoryResult.data?.let { myData ->
                toolList = myData.tool
                digitalList = myData.digital
                supermarketList = myData.supermarket
                fashionList = myData.fashion
                nativeList = myData.native
                toyList = myData.toy
                mobileList = myData.mobile
                beautyList = myData.beauty
                homeList = myData.home
                bookList = myData.book
                sportList = myData.sport
            }

        }

        is NetworkResult.Error -> {
            loading = false
            // Log.e("dorrri2", amazingItemResult.message.toString())
        }

        is NetworkResult.Loading -> {
            loading = true
        }

    }


    if (loading) {
        val config = LocalConfiguration.current
        MyLoading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            CategoryItem(
                stringResource(id = R.string.industrial_tools_and_equipment),
                toolList
            )
            CategoryItem(
                title = stringResource(id = R.string.digital_goods),
                subList = digitalList
            )
            CategoryItem(
                title = stringResource(id = R.string.mobile),
                subList = mobileList
            )
            CategoryItem(
                title = stringResource(id = R.string.fashion_and_clothing),
                subList = fashionList
            )
            CategoryItem(
                title = stringResource(id = R.string.supermarket_product),
                subList = supermarketList
            )
            CategoryItem(
                title = stringResource(id = R.string.native_and_local_products),
                subList = nativeList
            )
            CategoryItem(
                title = stringResource(id = R.string.toys_children_and_babies),
                subList = toyList
            )
            CategoryItem(
                title = stringResource(id = R.string.beauty_and_health),
                subList = beautyList
            )
            CategoryItem(
                title = stringResource(id = R.string.home_and_kitchen),
                subList = homeList
            )
            CategoryItem(
                title = stringResource(id = R.string.books_and_stationery),
                subList = bookList
            )
            CategoryItem(
                title = stringResource(id = R.string.sports_and_travel),
                subList = sportList
            )



        }
    }


}