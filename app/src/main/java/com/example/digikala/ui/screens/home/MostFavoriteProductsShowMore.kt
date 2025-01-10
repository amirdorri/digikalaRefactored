package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing

@Composable
fun MostFavoriteProductsShowMore() {

    Column(
        modifier = Modifier
            .size(width = 180.dp, height = 320.dp)
            .padding(
                end = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.semiSmall,
                top = MaterialTheme.spacing.semiLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

            Icon(
                painter = painterResource(R.drawable.show_more),
                contentDescription = null,
                tint = MaterialTheme.colors.DarkCyan,
                modifier = Modifier.size(40.dp, 40.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )
    }
}