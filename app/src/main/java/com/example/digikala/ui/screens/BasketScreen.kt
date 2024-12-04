package com.example.digikala.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.hardware.lights.Light
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.digikala.ui.theme.selecedBottomBar


@Composable
fun basketScreen(navController: NavHostController){

    if (isSystemInDarkTheme()){
        BasketDark()
    }else{
        BasketLight()
    }

}
@Composable
fun BasketLight(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "basket screen",
            color = MaterialTheme.colors.selecedBottomBar
        )

    }

}

@Composable
fun BasketDark(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "basket screen",
            color = MaterialTheme.colors.selecedBottomBar
        )

    }

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_NO)
fun BasketLightPreview(){
    BasketLight()

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun BasketDarkPreview(){

    BasketDark()
}