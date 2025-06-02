package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController)

}

@Composable
fun Basket(
    navController: NavHostController,
    viewmodel: BasketViewModel = hiltViewModel()
) {
    val currentCartItemsCount =  viewmodel.currentItemsCount.collectAsState(0)
    val nextCartItemsCount =   viewmodel.nextItemsCount.collectAsState(0)
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        stringResource(R.string.cart),
        stringResource(R.string.next_cart_list),
    )

    Column {

        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor =  MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.digikalaRed,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)
                )
            },
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
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
                            val cartCounter = if (index == 0) {
                                currentCartItemsCount.value
                            } else nextCartItemsCount.value

                            if (cartCounter > 0) {
                                Spacer(Modifier.width(10.dp))
                                SetBadgeToTab(
                                    selectedTabIndex,
                                    index,
                                    cartCounter
                                )
                            }
                        }
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ShoppingCart(navController)
            1 -> NextShoppingCart(navController)
        }
    }
}

