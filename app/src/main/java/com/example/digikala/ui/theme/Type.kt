package com.example.digikala.ui.theme

//import androidx.compose.material3.Typography
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.digikala.R

val font_standard = FontFamily(Font(R.font.iranyekan))
val font_medium = FontFamily(Font(R.font.iranyekanmedium))
val font_bold = FontFamily(Font(R.font.iranyekanbold))

val Typography.extraBoldNumber: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,


        )

val Typography.extraSmall: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        //fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 25.sp


    )

val Typography.veryExtraSmall: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontSize = 10.sp,



    )

// Set of Material typography styles to start with
val Typography = Typography(

    body1 = TextStyle(
        fontFamily = font_medium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.sp
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),

    body2 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 26.sp

    ),

    h1 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 25.sp
    ),

    h2 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 25.sp
    ),

    h3 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),

    h4 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 25.sp
    ),

    h5 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 25.sp
    ),

    h6 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 25.sp
    ),


    )