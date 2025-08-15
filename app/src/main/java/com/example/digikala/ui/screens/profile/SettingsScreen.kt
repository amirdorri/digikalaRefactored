package com.example.digikala.ui.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.BuildConfig
import com.example.digikala.MainActivity
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.DigikalaDarktRed
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.DIGI_BUG
import com.example.digikala.util.Constants.DIGI_FAQ
import com.example.digikala.util.Constants.DIGI_PRIVACY
import com.example.digikala.util.Constants.DIGI_SCORE
import com.example.digikala.util.Constants.DIGI_TERMS
import com.example.digikala.util.Constants.DIGI_TRUELEARN
import com.example.digikala.util.Constants.USER_TOKEN
import com.example.digikala.viewmodel.BasketViewModel
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun SettingsScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
           // .verticalScroll(rememberScrollState())
    ) {
        SettingHeader(navController)

        SettingMenuSection(navController)
        
        Spacer(Modifier.weight(1f))
        SettingBranding()
    }
}

@Composable
fun SettingHeader(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.large, end = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.setting)
        )

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.small
                    ),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }
    }
}

@Composable
fun SettingMenuSection(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
) {

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.HelpCenter,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.repetitive_questions),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_FAQ}"
        )

    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PrivacyTip,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.privacy),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_PRIVACY}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.OtherHouses,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.terms_of_use),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TERMS}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Call,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.contact_us),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TRUELEARN}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PestControl,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.error_report),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_BUG}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.StarRate,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.rating_to_digikal),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_SCORE}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Language,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.changing_lang),
        haveDivider = true,
        addCompose = {
            ChangeLanguage()
        }
    )

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Logout,
                contentDescription = "",
                tint = MaterialTheme.colors.DigikalaDarktRed
            )
        },
        text = stringResource(id = R.string.sign_out),
        textColor = MaterialTheme.colors.DigikalaDarktRed,
        haveDivider = false,
        addCompose = {}
    ) {
        logOut(navController, dataStore, profileViewModel, basketViewModel)
    }

}


fun logOut(
    navController: NavHostController,
    dataStore: DataStoreViewModel,
    profileViewModel: ProfileViewModel,
    basketViewModel: BasketViewModel
) {
    basketViewModel.deleteAllItems()
    dataStore.apply {
        saveUserToken("null")
        saveUserId("null")
        saveUserPhone("null")
        saveUserPassword("null")
        saveUserName("null")
    }
    USER_TOKEN = "null"
    profileViewModel.screenState = ProfileScreenState.LOGIN_STATE
    navController.navigate(Screen.Profile.route)
}


@SuppressLint("ContextCastToActivity")
@Composable
fun ChangeLanguage(dataStore: DataStoreViewModel = hiltViewModel()) {

    val activity = LocalContext.current as Activity
    val lang = dataStore.getUserLanguage()
    val checkState by remember { mutableStateOf(lang) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.english)
        )

        Switch(
            checked = (checkState == "fa"),
            onCheckedChange = {
                dataStore.saveUserLanguage(if (lang == "en") "fa" else "en")
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            }
        )

        Text(
            text = stringResource(R.string.farsi)
        )
    }
}


@Composable
fun SettingBranding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(60.dp).padding(8.dp),
            painter = painterResource(id = R.drawable.amirdori),
            contentDescription = ""
        )
        Image(
            modifier = Modifier.width(100.dp),
            painter = painterResource(id = R.drawable.digi_red_english),
            contentDescription = ""
        )

        Text(
            text = stringResource(id = R.string.version_app, BuildConfig.VERSION_NAME),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.semiDarkColor
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.truelearn_technical_team),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkColor
            )
           // Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

        }
    }
}
