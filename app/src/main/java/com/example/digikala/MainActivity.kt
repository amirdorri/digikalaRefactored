package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
//import androidx.compose.material.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digikala.navigation.BottomNavigationBar
import com.example.digikala.navigation.setupNavGraph
import com.example.digikala.ui.components.AppConfig
import com.example.digikala.ui.components.ChangeStatusBarColor
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.util.Constants.ENGLISH_LANG
import com.example.digikala.util.Constants.PERSIAN_LANG
import com.example.digikala.util.Constants.USER_LANGUAGE
import com.example.digikala.util.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter"
    )
    // @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)

                AppConfig()
               // Log.e("1010", USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == ENGLISH_LANG){
                    androidx.compose.ui.unit.LayoutDirection.Ltr
                }else{
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
                        setupNavGraph(navController = navController)

                    }
                }

            }
        }
    }
}
