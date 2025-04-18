package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screens.basket.BasketScreen
import com.example.digikala.ui.screens.category.CategoryScreen
import com.example.digikala.ui.screens.checkout.CheckoutScreen
import com.example.digikala.ui.screens.checkout.ConfirmPurchaseScreen
import com.example.digikala.ui.screens.home.HomeScreen
import com.example.digikala.ui.screens.home.WebPageScreen
import com.example.digikala.ui.screens.profile.ProfileScreen
import com.example.digikala.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Category.route) {
            CategoryScreen(navController)
        }

        composable(Screen.Basket.route) {
            BasketScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(navController)
        }
        composable(
            Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(navController, orderId, orderPrice)
                }
            }
        }

        composable(
            Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController, url)
            }

        }


    }

}
