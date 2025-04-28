package com.example.digikala.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Category : Screen("category_screen")
    object Basket : Screen("basket_screen")
    object Profile : Screen("profile_screen")
    object WebView : Screen("webView_screen")
    object Checkout : Screen("checkout_screen")
    object ConfirmPurchase : Screen("confirmPurchase_screen")
    object ProductDetailScreen : Screen("ProductDetails_screen")
    object TechnicalFeaturesScreen : Screen("TechnicalFeatures_screen")
    object ProductDescScreen : Screen("ProductDescription_Screen")

    fun withArgs(vararg args:Any):String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}