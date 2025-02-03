package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ShoppingCart(
    viewModel : BasketViewModel = hiltViewModel()
) {

    val currentCartItems = remember {
        mutableStateOf(emptyList<CartItem>())
    }
    LaunchedEffect(true) {
        viewModel.currentCartItems.collectLatest { list ->
            currentCartItems.value = list
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp)
    ) {
        if (currentCartItems.value.isEmpty()){
            item { EmptyBasket() }
            item { SuggestListSection() }
        } else{

            items(currentCartItems.value) { item ->
                CartItemCard(item)
            }
        }


    }
}