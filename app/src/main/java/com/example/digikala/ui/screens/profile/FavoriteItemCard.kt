package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.profile.FavoriteItems
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaDarktRed
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FavoriteItemCard(
    navController: NavHostController,
    favItem: FavoriteItems,
    coroutineScope: CoroutineScope,
    modalSheetState: ModalBottomSheetState,
    onItemSelected: (FavoriteItems) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.ProductDetailScreen.withArgs(favItem.id)) }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(favItem.image),
                contentDescription = "",
                modifier = Modifier
                    .width(120.dp)
                    .height(100.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = favItem.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = MaterialTheme.spacing.medium),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.in_stock),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colors.DarkCyan
                        )
                        Text(
                            text = favItem.seller,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.semiDarkColor,
                        )
                    }

                    Row {
                        Text(
                            text = DigitHelper.digitByLocate(favItem.star.toString()),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.semiDarkColor,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_star),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colors.amber
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colors.DigikalaDarktRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),
                    ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(favItem.discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Column {

                        Row {
                            Text(
                                text = DigitHelper.digitBySeparator(
                                    DigitHelper.digitByLocate(
                                        favItem.price.toString()
                                    )
                                )
                            )
                            Image(
                                painter = painterResource(id = R.drawable.toman),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(MaterialTheme.spacing.semiLarge)
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )
                        }

                        Text(
                            text = DigitHelper.digitBySeparator(
                                DigitHelper.digitByLocate(
                                    DigitHelper.applyDiscount(
                                        favItem.price,
                                        favItem.discountPercent
                                    ).toString()
                                )
                            ),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body2,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(0.5f)
                            .clickable {
                                coroutineScope.launch {
                                    onItemSelected(favItem)
                                    if (modalSheetState.isVisible) {
                                        modalSheetState.hide()
                                    } else {
                                        modalSheetState.show()
                                    }
                                }

                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.digi_trash),
                            contentDescription = "digi trash",
                            tint = Color.Gray
                        )
                        Text(
                            text = stringResource(id = R.string.remove_from_list),
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text(
                            text = stringResource(R.string.watch_and_buy_product),
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.DarkCyan
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = MaterialTheme.colors.DarkCyan
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .alpha(0.2f)
                        .padding(MaterialTheme.spacing.small),
                    color = MaterialTheme.colors.grayAlpha
                )
            }
        }
    }
}