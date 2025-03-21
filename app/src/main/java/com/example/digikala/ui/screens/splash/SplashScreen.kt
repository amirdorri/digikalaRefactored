package com.example.digikala.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.splashBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash()
    LaunchedEffect(true) {
        delay(3000)

        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route){
                inclusive = true
            }
        }
    }
}

@Composable
fun Splash() {

    Box( // main
        modifier = Modifier
            .background(androidx.compose.material.MaterialTheme.colors.splashBg)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.digi_logo), contentDescription = ""
        )

        Box( //text
            modifier = Modifier
            .fillMaxSize().
            padding(100.dp),
        contentAlignment = Alignment.BottomCenter
        ){
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = R.drawable.digi_txt_white), contentDescription = ""
            )

        }

        Box( //loading
            modifier = Modifier
                .fillMaxSize().
                padding(20.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Loading3Dots(false)

        }

    }

}




