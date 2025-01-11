package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.components.IconWithRotate
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.ENGLISH_LANG
import com.example.digikala.util.Constants.USER_LANGUAGE

@Composable
fun AmazingOfferCard(topImageResId: Int, bottomImageResId: Int) {

    Column(
        modifier = Modifier
            .width(160.dp)
            .height(380.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(Modifier.height(60.dp))

        Image(
            painter = amazingLogoChangerByLang(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
        )
        Spacer(Modifier.height(15.dp))

        Image(
            painter = painterResource(bottomImageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
        Spacer(Modifier.height(40.dp))

        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.h6,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            IconWithRotate(Icons.Filled.KeyboardArrowLeft)
        }
    }
}

@Composable
private fun amazingLogoChangerByLang() : Painter {

    return if (USER_LANGUAGE == ENGLISH_LANG) {
        painterResource(id = R.drawable.amazing_en)
    } else painterResource(id = R.drawable.amazings)
}