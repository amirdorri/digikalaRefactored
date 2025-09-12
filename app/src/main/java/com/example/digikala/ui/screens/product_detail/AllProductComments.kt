package com.example.digikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.PRODUCT_COMMENTS
import com.example.digikala.util.Constants.USER_COMMENTS
import com.example.digikala.util.DigitHelper
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.util.DigitHelper.gregorianToJalali
import com.example.digikala.viewmodel.CommentsViewModel

@Composable
fun AllProductComments(
    viewModel: CommentsViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String,
    commentsType: String
) {
    Log.d("PagingDebug", "🎯 AllProductCommentsScreen: productId=$productId, type=$commentsType")

    // بر اساس نوع کامنت، مناسب رو صدا بزن
    LaunchedEffect(commentsType, productId) {
        when (commentsType) {
            USER_COMMENTS -> {
                Log.d("PagingDebug", "🚀 Loading user comments")
                viewModel.getUserComments()
            }
            else -> { // PRODUCT_COMMENTS
                Log.d("PagingDebug", "🚀 Loading product comments for productId: $productId")
                viewModel.getCommentList(productId)
            }
        }
    }

    // بر اساس نوع، مناسب رو collect کن
    val commentsList = when (commentsType) {
        USER_COMMENTS -> viewModel.userCommentsList.collectAsLazyPagingItems()
        else -> viewModel.productCommentsList.collectAsLazyPagingItems()
    }

    Log.d("PagingDebug", "📊 Comments LoadState: ${commentsList.loadState}")
    Log.d("PagingDebug", "📊 Comments ItemCount: ${commentsList.itemCount}")

    Column {
        Text(
            text = if (commentsType == USER_COMMENTS) "نظرات من" else "نظرات محصول",
            //style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        when (val refreshState = commentsList.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "خطا در بارگذاری نظرات",
                            color = Color.Red
                        )
                        Text(
                            text = refreshState.error.localizedMessage ?: "خطای نامشخص",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            is LoadState.NotLoading -> {
                if (commentsList.itemCount == 0) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "هیچ نظری یافت نشد",
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            count = commentsList.itemCount,
                            key = { index ->
                                val comment = commentsList[index]
                                if (comment != null) {
                                    "${comment._id}_$index"
                                } else {
                                    "loading_$index"
                                }
                            }
                        ) { index ->
                            val comment = commentsList[index]
                            if (comment != null) {
                                CommentsItem(comment)
                            } else {
                                // Loading placeholder
                                CommentItemPlaceholder()
                            }
                        }

                        // Loading more indicator
                        when (val appendState = commentsList.loadState.append) {
                            is LoadState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            is LoadState.Error -> {
                                item {
                                    Text(
                                        text = "خطا در بارگذاری بیشتر: ${appendState.error.localizedMessage}",
                                        color = Color.Red,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(comment: ProductComment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = comment.userName ?: "کاربر ناشناس",
                //style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = comment.description ?: "",
                //style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = comment.createdAt ?: "",
                //style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CommentItemPlaceholder() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(16.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(12.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
private fun CommentsItem(item: ProductComment) {

    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()
    val context = LocalContext.current

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
                text = digitByLocate(item.star.toString() + ".0"),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
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

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )

        Row(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconSuggestion),
                contentDescription = "",
                Modifier
                    .size(16.dp)
                    .rotate(iconRotation),
                tint = colorSuggestion
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
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large
                ),
            text = item.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )

        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large,
                ),
            text = item.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )
    }
}


