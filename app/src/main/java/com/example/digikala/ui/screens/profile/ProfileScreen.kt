package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.CenterBannerItem
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants
import com.example.digikala.util.Constants.DIGIPLUS_URL
import com.example.digikala.util.Constants.DIGI_CLUB
import com.example.digikala.util.Constants.DIGI_WALLET
import com.example.digikala.util.Constants.PRODUCT_COMMENTS
import com.example.digikala.util.Constants.TURLEARN_CONTACT
import com.example.digikala.util.Constants.USER_COMMENTS
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val token = dataStore.getUserToken()
    if (!token.isNullOrBlank() && token != "null") {
        Profile(navController)
    } else {
        when (profileViewModel.screenState) {
            ProfileScreenState.LOGIN_STATE -> {
                LoginScreen()
            }

            ProfileScreenState.REGISTER_STATE -> {
                RegisterScreen()
            }

            ProfileScreenState.PROFILE_STATE -> {
                Profile(navController)
            }
        }
    }
}

@Composable
fun Profile(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp),
    ) {
        item { ProfileTopBarSection(navController) }
        item { ProfileHeaderSection(navController) }
        item { ProfileMiddleSection(navController) }
        item { MyOrdersSection() }
        item { CenterBannerItem(painter = painterResource(id = R.drawable.digiclub1),navController = navController) }
        item { ProfileMenuSection(navController) }
        item { CenterBannerItem(painter = painterResource(id = R.drawable.digiclub2), navController = navController) }
    }
}


@Composable
private fun ProfileMenuSection(navController: NavHostController) {

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_plus_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.digi_plus),
        haveDivider = true
    ) {
        navController.navigate(route = Screen.WebView.route + "?url=$DIGIPLUS_URL")
    }
    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_fav_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.fav_list),
        haveDivider = true
    ){
        navController.navigate(Screen.FavoriteList.route)
    }

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_comments_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.my_comments),
        haveDivider = true
    ){
        navController.navigate(Screen.AllProductComments.withArgs("1", "1", USER_COMMENTS))
    }
    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_adresses_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.addresses),
        haveDivider = true
    ){
        navController.navigate(Screen.AddressScreen.withArgs(-1))
    }

    MenuRowItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.digi_profile_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.profile_data),
        haveDivider = false
    ) {
        navController.navigate(Screen.UserAccount.route)
    }


}


@Composable
private fun MyOrdersSection() {
    Text(
        modifier = Modifier.padding(MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.my_orders),
    )

    LazyRow() {
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.unpaid),
                painter = painterResource(id = R.drawable.digi_unpaid)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.processing),
                painter = painterResource(id = R.drawable.digi_processing)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.my_orders),
                painter = painterResource(id = R.drawable.digi_delivered)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.canceled),
                painter = painterResource(id = R.drawable.digi_cancel)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.returned),
                painter = painterResource(id = R.drawable.digi_returned)
            )
        }
    }
}


@Composable
private fun MyOrdersItem(
    text: String,
    painter: Painter
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor,
                text = text
            )
        }
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(90.dp)
                .alpha(0.4f),
            color = Color.LightGray,
        )
    }
}

@Composable
private fun ProfileTopBarSection(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            navController.navigate(Screen.SettingsScreen.route)
        }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.digi_settings
                ), contentDescription = "",
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small
                    )
                    .size(MaterialTheme.spacing.semiLarge),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }

        IconButton(onClick = {
            navController.navigate(Screen.Home.route)
        }) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }
    }
}

@Composable
private fun ProfileHeaderSection(
    navController: NavHostController
) {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    if (Constants.USER_NAME != "null") {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = Constants.USER_NAME,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h4
        )
    } else {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.UserAccount.route) },
            text = stringResource(id = R.string.completion_of_user_information),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.DarkCyan,
            style = MaterialTheme.typography.h5
        )
    }


    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.semiDarkColor,
        text = digitByLocate(Constants.USER_PHONE)
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Row(
            modifier = Modifier
                .weight(0.49f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.semiMedium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.semiDarkColor,
                        text = "${digitByLocate("975")} "
                    )
                    Text(
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.semiDarkColor,
                        text = stringResource(R.string.score)
                    )
                }

                Text(
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(id = R.string.digikala_score)
                )
            }


        }

        Divider(
            modifier = Modifier
                .width(2.dp)
                .height(60.dp)
                .alpha(0.2f),
            color = Color.LightGray,
        )

        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate(route = Screen.WebView.route + "?url=$DIGI_WALLET")
                }
                .weight(0.49f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_wallet),
                contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
            )

            Text(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.digikala_wallet_active)
            )

        }

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
}


@Composable
private fun ProfileMiddleSection(navController: NavHostController) {

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            Modifier.clickable { navController.navigate(Screen.UserAccount.route) },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.digi_user),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor,
                text = stringResource(R.string.auth)
            )
        }

        Column(
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.WebView.route + "?url=$DIGI_CLUB")
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.digi_club),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor,
                text = stringResource(R.string.club)
            )
        }


        Column(
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.WebView.route + "?url=$TURLEARN_CONTACT")
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_contact_us),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor,
                text = stringResource(R.string.contact_us)
            )
        }

    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )


}
