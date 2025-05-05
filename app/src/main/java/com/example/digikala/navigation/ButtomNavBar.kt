package com.example.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.R
import com.example.digikala.ui.screens.basket.IconWithBadge
import com.example.digikala.ui.theme.bottomBar
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.unSelectedBottomBar
import com.example.digikala.viewmodel.BasketViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    val items = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.home_fill),
            deselectedIcon = painterResource(id = R.drawable.home_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(id = R.drawable.category_fill),
            deselectedIcon = painterResource(id = R.drawable.category_outline)
        ),

        BottomNavItem(
            name = stringResource(id = R.string.basket),
            route = Screen.Basket.route,
            selectedIcon = painterResource(id = R.drawable.cart_fill),
            deselectedIcon = painterResource(id = R.drawable.cart_outline),
        ),

        BottomNavItem(
            name = stringResource(id = R.string.my_digikala),
            route = Screen.Profile.route,
            selectedIcon = painterResource(id = R.drawable.user_fill),
            deselectedIcon = painterResource(id = R.drawable.user_outline)
        )

    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
        BottomNavigation(
            modifier = Modifier.height(60.dp),
            backgroundColor = MaterialTheme.colors.bottomBar,
            elevation = 5.dp
        ) {
            val cartCounter by basketViewModel.currentItemsCount.collectAsState(0)
            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {

                            if (selected) {

                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                        icon = item.selectedIcon,
                                        cartCounter = cartCounter
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.selectedIcon,
                                        contentDescription = item.name
                                    )
                                }

                            } else {

                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                        icon = item.deselectedIcon,
                                        cartCounter = cartCounter
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.deselectedIcon,
                                        contentDescription = item.name
                                    )
                                }
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}