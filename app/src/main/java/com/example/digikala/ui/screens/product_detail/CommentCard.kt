package com.example.digikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.PRODUCT_COMMENTS
import com.example.digikala.util.Constants.USER_COMMENTS
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.util.DigitHelper.gregorianToJalali

@Composable
fun CommentCard(item: ProductComment) {

    val context = LocalContext.current
    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
    val date = item.createdAt.substringBefore("T").split("-")
    val year = date[0].toInt()
    val month = date[1].toInt()
    val day = date[2].toInt()
    var iconRotation = 0f

    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Oranges
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotation = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkColor
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotation = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotation = 0f
        }
    }


    Card(
        modifier = Modifier
            .width(260.dp)
            .height(210.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium,
            ),
        shape = MaterialTheme.roundedShape.small,
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = item.title,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5,
            )

            Row(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconSuggestion),
                    tint = colorSuggestion,
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(iconRotation)
                )
                Text(
                    text = textSuggestion,
                    Modifier
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                    color = colorSuggestion
                )
            }

            Text(
                text = item.description,
                Modifier.weight(1f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = digitByLocate(gregorianToJalali(year, month, day)),
                    color = MaterialTheme.colors.semiDarkColor,
                    style = MaterialTheme.typography.h6,
                )
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "",
                    Modifier
                        .size(20.dp)
                        .padding(horizontal = MaterialTheme.spacing.small),
                    tint = MaterialTheme.colors.grayAlpha
                )
                Text(
                    text = item.userName,
                    color = MaterialTheme.colors.grayAlpha,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}


@Composable
fun ShowAllComments(
    navController: NavHostController,
    productId: String,
    commentCount: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .padding(vertical = MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(Screen.AllProductComments.withArgs(productId, commentCount, PRODUCT_COMMENTS))
            }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.show_more),
            contentDescription = "",
            tint = MaterialTheme.colors.DarkCyan,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(R.string.see_all),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            textAlign = TextAlign.Center,
        )

    }
}