package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)

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
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = { navController.navigate(it.route) },
                                modifier = Modifier.systemBarsPadding()
                                )
                        }
                    ) {
                        SetupNavGraph(
                            navController = navController,
                            modifier = Modifier.systemBarsPadding()
                            )
                    }
                }
            }
        }
    }
}

























//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    @SuppressLint(
//        "UnusedMaterial3ScaffoldPaddingParameter",
//        "UnusedMaterialScaffoldPaddingParameter")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        lateinit var navController: NavHostController
//        super.onCreate(savedInstanceState)
//
////        ZarinpalPurchase.purchase(
////            activity = this,
////            amount = 1000,
////            description = "TEST") {
////            Log.e("DESC_ID", it)
////        }
//        setContent {
//            DigikalaTheme {
//
//                navController = rememberNavController()
//                ChangeStatusBarColor(navController = navController)
//
//                AppConfig()
//
//                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
//                    androidx.compose.ui.unit.LayoutDirection.Ltr
//                } else {
//                    androidx.compose.ui.unit.LayoutDirection.Rtl
//                }
//
//                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
//                CompositionLocalProvider(LocalLayoutDirection provides direction) {
//                    Scaffold(
//                        bottomBar = {
//                            BottomNavigationBar(
//                                navController = navController,
//                                onItemClick = { navController.navigate(it.route) },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .navigationBarsPadding() // 👈 Cleaner than manual padding
//                            )
//                        }
//                    ) { innerPadding ->
//
//                        val topPadding = WindowInsets.statusBars
//                            .asPaddingValues()
//                            .calculateTopPadding()
//                            .value
//
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(innerPadding)
//                                .padding(top = topPadding.dp)
//                        ) {
//                            SetupNavGraph(navController = navController)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

// qwen
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//
//        setContent {
//            DigikalaTheme {
//
//                val navController = rememberNavController()
//                ChangeStatusBarColor(navController = navController)
//                AppConfig()
//
//                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
//                    androidx.compose.ui.unit.LayoutDirection.Ltr
//                } else {
//                    androidx.compose.ui.unit.LayoutDirection.Rtl
//                }
//
//                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
//
//                CompositionLocalProvider(LocalLayoutDirection provides direction) {
//                    Scaffold(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(WindowInsets.systemBars.asPaddingValues()),
//                        bottomBar = {
//                            BottomNavigationBar(
//                                navController = navController,
//                                onItemClick = {
//                                    navController.navigate(it.route)
//                                }
//                            )
//                        }
//                    ) { innerPadding ->
//                        SetupNavGraph(
//                            navController = navController,
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}