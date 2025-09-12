package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryListSection(
    navController : NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var categoryList by remember { mutableStateOf<List<MainCategory>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    val categoryResult by viewModel.categories.collectAsState()

    when (categoryResult) {
        is NetworkResult.Success -> {
            categoryList = categoryResult.data ?: emptyList()
            loading = false
            // Log.e("dorri", bannersList.toString())
        }

        is NetworkResult.Error -> {
            loading = false
            //Log.e("dorri1", cardResult.toString())
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
    ) {
        Text(
            text = stringResource(R.string.category_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText
        )
        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxWidth(),
        ) {
            categoryList.forEach { item ->
                CircularCategoryItem(item, navController)
            }
        }
    }
}


@Composable
fun CircularCategoryItem(
    item : MainCategory,
    navController : NavHostController
) {

    Column(
        modifier = Modifier
            .size(100.dp, 160.dp)
            .clickable {
                navController.navigate(Screen.SubCategoryScreen.withArgs(item._id))
            }
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        ) {

        Image(
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp, 100.dp)
                .padding(vertical = MaterialTheme.spacing.extraSmall)
        )
        Text(
            text = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.extraSmall),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}