package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants

@Composable
fun EmptyNextShoppingList() {
    val config = LocalConfiguration.current
    val isUserLoggedIn = Constants.USER_TOKEN != "null"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isUserLoggedIn) Modifier.height(config.screenHeightDp.dp - 60.dp)
                else Modifier.wrapContentHeight() // Allows content to stay closer to the dialog
            )
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (isUserLoggedIn) Arrangement.Center else Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_next_chart),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(180.dp)
                .width(200.dp)
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.next_cart_list_is_empty),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.fillMaxWidth(0.8f),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.next_cart_list_is_empty_msg),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.fillMaxWidth(0.8f),
            textAlign = TextAlign.Center
        )
    }
}
