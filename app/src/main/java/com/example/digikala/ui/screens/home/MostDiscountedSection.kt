package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.home.AmazingItem
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MostDiscountedSection(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var mostDiscountedList by remember { mutableStateOf<List<StoreProduct>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    val mostDiscountedResult by viewModel.mostDiscountedItems.collectAsState()

    when (mostDiscountedResult) {
        is NetworkResult.Success -> {
            mostDiscountedList = mostDiscountedResult.data ?: emptyList()
            loading = false
            // Log.e("dorri", bannersList.toString())
        }

        is NetworkResult.Error -> {
            loading = false
            //Log.e("dorri1", cardResult.toString())
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            text = stringResource(id = R.string.most_discounted_products),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
        )
        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            for (item in mostDiscountedList) {
                MostDiscountedCard(navController, item)
            }
        }
    }
}