package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.compose.material.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digikala.navigation.BottomNavigationBar
import com.example.digikala.navigation.SetupNavGraph
import com.example.digikala.ui.components.AppConfig
import com.example.digikala.ui.components.ChangeStatusBarColor
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.util.Constants.ENGLISH_LANG
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.LocaleUtils
import com.example.digikala.util.ZarinpalPurchase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)

//        ZarinpalPurchase.purchase(
//            activity = this,
//            amount = 1000,
//            description = "TEST") {
//            Log.e("DESC_ID", it)
//        }
        setContent {
            DigikalaTheme {

                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)

                AppConfig()

                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
                    androidx.compose.ui.unit.LayoutDirection.Ltr
                } else {
                    androidx.compose.ui.unit.LayoutDirection.Rtl
                }

                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
