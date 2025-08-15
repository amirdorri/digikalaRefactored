package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.digikala.ui.screens.product_detail.AllProductComments
import com.example.digikala.ui.screens.product_detail.NewCommentScreen
import com.example.digikala.ui.screens.product_detail.ProductChartScreen
import com.example.digikala.ui.screens.product_detail.ProductDescScreen
import com.example.digikala.ui.screens.product_detail.ProductDetailsScreen
import com.example.digikala.ui.screens.product_detail.ProductTechnicalFeaturesScreen
import com.example.digikala.ui.screens.profile.AddAddressScreen
import com.example.digikala.ui.screens.profile.AddressScreen
import com.example.digikala.ui.screens.profile.FavoriteListScreen
import com.example.digikala.ui.screens.profile.ProfileScreen
import com.example.digikala.ui.screens.profile.SettingsScreen
import com.example.digikala.ui.screens.profile.UserAccountScreen
import com.example.digikala.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
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
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice") {
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

        composable(
            Screen.ProductDetailScreen.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailsScreen(navController, productId)
            }
        }

        composable(
            Screen.ProductDescScreen.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescScreen(navController, description)
            }
        }

        composable(
            Screen.TechnicalFeaturesScreen.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(navController, jsonString)
            }
        }

        composable(
            Screen.NewCommentScreen.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }

            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("productName")?.let { imageUrl ->
                        NewCommentScreen(navController, productId, productName, imageUrl)
                    }

                }
            }
        }

        composable(
            Screen.AllProductComments.route + "/{productId}/{commentCount}/{pageName}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("commentCount") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("pageName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentCount")?.let { commentCount ->
                    it.arguments!!.getString("pageName")?.let { pageName ->

                        AllProductComments(
                            navController = navController,
                            productId = productId,
                            commentsCount = commentCount,
                            pageName = pageName
                        )
                    }
                }
            }
        }

        composable(
            Screen.ProductChartScreen.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { productId ->
                ProductChartScreen(navController, productId)
            }
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }

        composable(Screen.UserAccount.route) {
            UserAccountScreen(navController)
        }

        composable(Screen.FavoriteList.route) {
            FavoriteListScreen(navController)
        }

        composable(
            route = Screen.AddressScreen.route + "/{isFromBasket}",
            arguments = listOf(
                navArgument("isFromBasket") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("isFromBasket")?.let { isFromBasket ->
                AddressScreen(
                    navController = navController,
                    isFromBasket = isFromBasket.toInt()
                )
            }

        }

        composable(route = Screen.AddAddressScreen.route) {
            AddAddressScreen(navController = navController)
        }


    }
}
