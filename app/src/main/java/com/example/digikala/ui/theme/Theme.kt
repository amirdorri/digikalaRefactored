package com.example.digikala.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Typography
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
//import androidx.compose.material.darkColorScheme
import androidx.compose.material.darkColors
//import androidx.compose.material.dynamicDarkColorScheme
//import androidx.compose.material.dynamicLightColorScheme
//import androidx.compose.material.lightColorScheme
//import androidx.compose.material.lightColorScheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

//DarkColorPalette
private val DarkColorPalette = darkColors(
    primary = Purple200,
    secondary = Teal200,
    primaryVariant = Purple700
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    secondary = Teal200,
    primaryVariant = Purple700

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@JvmOverloads
@Composable
fun DigikalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
      DarkColorPalette
    }else{
        LightColorPalette
    }




    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content,
        shapes = Shapes()
    )


}