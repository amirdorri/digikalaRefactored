package com.example.digikala.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Category : Screen("category_screen")
    data object Basket : Screen("basket_screen")
    data object Profile : Screen("profile_screen")
    data object WebView : Screen("webView_screen")
    data object Checkout : Screen("checkout_screen")
    data object ConfirmPurchase : Screen("confirmPurchase_screen")
    data object ProductDetailScreen : Screen("ProductDetails_screen")
    data object TechnicalFeaturesScreen : Screen("TechnicalFeatures_screen")
    data object ProductDescScreen : Screen("ProductDescription_screen")
    data object NewCommentScreen : Screen("NewComment_screen")
    data object AllProductComments : Screen("allComment_screen")
    data object ProductChartScreen : Screen("ProductChart_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}