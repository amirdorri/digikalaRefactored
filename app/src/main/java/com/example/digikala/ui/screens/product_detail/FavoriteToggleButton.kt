package com.example.digikala.ui.screens.product_detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.profile.FavoriteItems
import com.example.digikala.ui.theme.darkText
import com.example.digikala.viewmodel.FavoriteListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteToggleButton(
    item: FavoriteItems,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {

    var checkedState by remember { mutableStateOf(false) }


    LaunchedEffect(true) {
        viewModel.isFavoriteItem(item.id).collectLatest {
            checkedState = it
        }
    }


    IconToggleButton(
        checked = checkedState,
        onCheckedChange = {
            checkedState = !checkedState

            if (checkedState) {
                viewModel.addFavoriteItem(item)
            } else {
                viewModel.deleteFavoriteItem(item)
            }
        }
    ) {
        val transition =
            updateTransition(checkedState, label = "icon transition")
        val tint by transition.animateColor(label = "iconColor") { isCheck ->
            if (isCheck) Color.Red else MaterialTheme.colors.darkText
        }

        Icon(
            imageVector = if (checkedState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(27.dp)
        )
    }
}

