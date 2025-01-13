package com.example.digikala.ui.screens.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.ui.screens.home.SearchBarSection
import com.example.digikala.viewmodel.CategoryViewmodel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(navController: NavHostController){

    Category(navController)


}

@Composable
fun Category(
    navController: NavHostController,
    viewModel: CategoryViewmodel = hiltViewModel()
) {
    LaunchedEffect(true) {
        refreshData(viewModel)
    }
    SwipeRefreshSection(viewModel, navController)


}

@Composable
private fun SwipeRefreshSection(viewModel: CategoryViewmodel, navController: NavHostController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshData(viewModel)
            }
        }) {

        LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)) {
            item { SearchBarSection() }
            item { SubCategorySection() }

        }

    }
}


private suspend fun refreshData(viewModel: CategoryViewmodel) {
    viewModel.getAllDataFromServer()
}