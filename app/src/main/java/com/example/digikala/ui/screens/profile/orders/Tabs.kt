package com.example.digikala.ui.screens.profile.orders

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Air
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.checkout.OrderFullDetail
import com.example.digikala.data.model.profile.TabItem
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.font_standard
import com.example.digikala.util.DigitHelper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(
    pagerState: PagerState,
    orders : List<OrderFullDetail>
) {

    val coroutineScope = rememberCoroutineScope()
    val list = listOf<TabItem>(
        TabItem(
            "${stringResource(id = R.string.waiting_for_purchase)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="0" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "0" }) },

        TabItem(
            "${stringResource(id = R.string.processing_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="1" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "1" }) },


        TabItem(
            "${stringResource(id = R.string.delivered_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="2" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "2" }) },


        TabItem(
            "${stringResource(id = R.string.canceled_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="3" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "3" }) },


        TabItem(
            "${stringResource(id = R.string.returned_orders)} (${DigitHelper.digitByLocate(orders.filter { it.orderStatus=="4" }.size.toString())})"
        ) { TabContentScreen(orders = orders.filter { it.orderStatus == "4" }) },
    )


    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        edgePadding = 1.dp,
        indicator = { position ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, position),
                height = 2.dp,
                color = MaterialTheme.colors.digikalaRed
            )
        }
    ) {
        list.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
//                icon = {
//                    Icon(
//                        imageVector = list[index].vector,
//                        contentDescription = null
//                    )
//                },
                text = {
                    Text(
                        text = list[index].title,
                        color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray,
                        fontFamily = font_standard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
            )
        }
    }
}