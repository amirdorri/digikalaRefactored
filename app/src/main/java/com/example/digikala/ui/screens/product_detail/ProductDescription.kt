package com.example.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.unSelecedBottomBar

@Composable
fun ProductDescription(
    navController: NavHostController,
    description: String,
    technicalFeature: String
) {

    var isDescription by remember { mutableStateOf(true) }
    if (description.isBlank()) isDescription = false

    var isTechnicalFeature by remember { mutableStateOf(true) }
    if (technicalFeature == "null") isTechnicalFeature = false

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Text(
        text = stringResource(id = R.string.product_desc),
        modifier = Modifier.padding(MaterialTheme.spacing.small),
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )

    if (isTechnicalFeature) {
        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clickable { navController.navigate(Screen.TechnicalFeaturesScreen.withArgs(technicalFeature)) },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.technical_specifications),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )

        }
    }

    if (isDescription) {
        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clickable {
                    navController.navigate(Screen.ProductDescScreen.withArgs(description))
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.product_introduce),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )

        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.semiMedium),
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.product_desc_feedback),
            style = MaterialTheme.typography.extraSmall,
            color = MaterialTheme.colors.unSelecedBottomBar,
        )
        Image(
            painter = painterResource(id = R.drawable.info),
            modifier = Modifier.size(20.dp),
            contentDescription = ""
        )

    }
}