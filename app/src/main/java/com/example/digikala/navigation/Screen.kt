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
    data object ProductDetailScreen : Screen("productDetails_screen")
    data object TechnicalFeaturesScreen : Screen("technicalFeatures_screen")
    data object ProductDescScreen : Screen("productDescription_screen")
    data object NewCommentScreen : Screen("newComment_screen")
    data object AllProductComments : Screen("allComment_screen")
    data object ProductChartScreen : Screen("ProductChart_screen")
    data object SettingsScreen : Screen("settings_screen")
    data object UserAccount : Screen("userAccount_screen")
    data object FavoriteList : Screen("favoriteList_screen")
    data object AddressScreen : Screen("address_screen")
    data object AddAddressScreen : Screen("addAddress_screen")
    data object TabLayoutScreen : Screen("TabLayout_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}