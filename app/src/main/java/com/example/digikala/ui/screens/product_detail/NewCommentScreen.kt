package com.example.digikala.ui.screens.product_detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.USER_NAME
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.CommentsViewModel
import com.example.digikala.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScreen(
    navController: NavHostController,
    productId: String,
    productName: String,
    imageUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController, productName, imageUrl)
        CommentForm(navController, productId = productId)
    }

}

@Composable
private fun Header(
    navController: NavHostController,
    productName: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
            )
        }

        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clip(MaterialTheme.roundedShape.small)
                .size(50.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.your_comment),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = productName,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor
            )
        }

    }

    Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)
}


@Composable
private fun CommentForm(
    navController: NavHostController,
    viewModel: CommentsViewModel = hiltViewModel(),
    productId: String
) {
    val context = LocalContext.current
    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val score = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }


    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.darkText,
        textAlign = TextAlign.Center
    )

    Slider(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large),
        value = sliderValue,
        onValueChange = { newValue ->
            sliderValue = newValue
        },
        onValueChangeFinished = {
            Log.e("onValueChangeFinished", sliderValue.toString())
        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            inactiveTickColor = MaterialTheme.colors.grayAlpha,
            activeTickColor = MaterialTheme.colors.amber,

            )
    )

    Divider(
        modifier = Modifier.padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp,
    )

    LaunchedEffect(true) {
        viewModel.newCommentResult.collectLatest { commentResult ->
            when (commentResult) {
                is NetworkResult.Success -> {
                    if (commentResult.message.equals("کامنت با موفقیت ثبت شد"))
                        //todo edit backend
                        navController.popBackStack()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${commentResult.message}")
                }

                is NetworkResult.Loading -> {

                }
            }

        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.say_your_comment),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText,
        )

        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall),
            text = stringResource(id = R.string.comment_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray)
                .fillMaxWidth(),
            value = commentTitle,
            onValueChange = { commentTitle = it },
            maxLines = 1,
            singleLine = true,
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Text(
            text = stringResource(id = R.string.comment_text),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.biggerMedium,
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall
                ),
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray)
                .height(100.dp),
            value = commentBody,
            onValueChange = { commentBody = it },
            maxLines = 3,
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.DarkCyan)
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.darkText,
            )
        }

        Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)

        if (loading){
            MyLoading(height = 60.dp, isDark = true)
        } else
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium),
                onClick = {
                    loading = true
                    val name = if (USER_NAME == "null") "کاربر بدون نام" else USER_NAME
                    val newComment = NewComment(
                        token = USER_TOKEN,
                        productId = productId,
                        star = (sliderValue - 1).toInt(),
                        title = commentTitle,
                        description = commentBody,
                        userName = name
                    )
                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(context, context.getString(R.string.comment_title_null), Toast.LENGTH_LONG).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(context, context.getString(R.string.comment_star_null), Toast.LENGTH_LONG).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(context, context.getString(R.string.comment_body_null), Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.set_new_comment),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.semiDarkColor
                )
            }
    }
}