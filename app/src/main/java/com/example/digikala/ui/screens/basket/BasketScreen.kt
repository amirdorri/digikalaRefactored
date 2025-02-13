package com.example.digikala.ui.screens.basket

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.selecedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.BasketViewModel
import com.example.digikala.viewmodel.CategoryViewmodel

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController)

}

@Composable
fun Basket(
    navController: NavHostController,
    viewmodel: BasketViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        stringResource(R.string.cart),
        stringResource(R.string.next_cart),
    )
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.digikalaRed,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)

                )
            },
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
        ) {
            tabTitles.fastForEachIndexed { index, title ->
                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold
                            )

                        }
                    }
                )
            }


        }

        when(selectedTabIndex){
            0 -> ShoppingCart()
            1 -> NextShoppingCart()
        }
    }


}

