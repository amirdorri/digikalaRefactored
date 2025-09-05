package com.example.digikala.data.model.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Vector

data class TabItem(
    val title : String,
    val screen : @Composable () -> Unit
    )
