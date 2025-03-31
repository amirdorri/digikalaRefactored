package com.example.digikala.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//val Purple80 = Color(0xffed1b34)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)

val Purple200 = Color(0xFFed1b34)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Colors.splashBg : Color
    @Composable
    get() = Color(0xffed1b34)

val Colors.selectedBottomBar : Color
    @Composable
    get() = if (isLight)  Color(0xff43474c) else Color(0xffcfd4da)

val Colors.unSelecedBottomBar : Color
    @Composable
    get() = if (isLight)  Color(0xffa4a1a1) else Color(0xff575a5e)


val Colors.searchBarBg : Color
    @Composable
    get() = if (isLight)  Color(0xfff1f0ee) else Color(0xff303235)


val Colors.darkText : Color
    @Composable
    get() = if (isLight)  Color(0xff414244) else Color(0xffd8d8d8)


val Colors.amber : Color
    @Composable
    get() = Color(0xffFFBF00)

val Colors.grayCategory : Color
    @Composable
    get() = Color(0xffF1F0EE)


val Colors.DigikalaLightRedText : Color
    @Composable
    get() = if (isLight) Color(0xff8d2633) else Color(0xFFFFFFFF)

val Colors.DigikalaLightRed : Color
    @Composable
    get() = if (isLight) Color(0xffef4056) else Color(0xff8d2633)

val Colors.bottomBar : Color
    @Composable
    get() = if (isLight) Color(0xFFFFFFFF) else Color(0xff303235)

val Colors.DigikalaLightGreen : Color
    @Composable
    get() = if (isLight) Color(0xff86bf3c) else Color(0xff3a531a)

val Colors.DigikalaDarktRed : Color
    @Composable
    get() = Color(0xffe6123d)

val Colors.DarkCyan : Color
    @Composable
    get() = Color(0xff0fabc6)

val Colors.digikalaRed : Color
    @Composable
    get() = Color(0xffed1b34)

val Colors.LightCyan : Color
    @Composable
    get() = Color(0xff17bfd3)

val Colors.Oranges : Color
    @Composable
    get() = Color(0xffff5722)

val Colors.semiDarkColor : Color
    @Composable
    get() = if (isLight) Color(0xff5c5e61) else Color(0xffd8d8d8)

val Colors.settingArrow : Color
    @Composable
    get() = if (isLight) Color(0xff9e9fb1) else Color(0xffd8d8d8)

val Colors.CursorColor : Color
    @Composable
    get() = Color(0xff018577)