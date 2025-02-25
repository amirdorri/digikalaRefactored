package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name:String,
    val route:String,
    val selectedIcon:Painter,
    val deselectedIcon:Painter,
    val badge: @Composable () -> Unit = {}
)
