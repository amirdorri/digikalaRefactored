package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.DataStoreViewModel


@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.digi_settings),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.small,
                                vertical = MaterialTheme.spacing.small,
                            )
                            .size(MaterialTheme.spacing.semiLarge),
                        tint = MaterialTheme.colors.selectedBottomBar
                    )
                    IconButton(onClick = {}) {
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
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Image(
                painter = painterResource(R.drawable.digi_smile),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                style = MaterialTheme.typography.h6,
                text = stringResource(id = R.string.loginTxt),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            MyEditText(
                value = "",
                placeholder = stringResource(R.string.phone_and_email),
                onValueChange = {

                }
            )
        }
        item {
            MyButton(
                text = stringResource(R.string.digikala_entry),
                onClick = {

                }
            )
        }
        item {
            Divider(
                color = MaterialTheme.colors.searchBarBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = MaterialTheme.spacing.medium)
            )
        }
        item {
            TermsAndRules(
                fullText = stringResource(R.string.terms_and_rules),
                underLinedText = listOf(
                    stringResource(R.string.terms_and_rules),
                    stringResource(R.string.privacy_and_rules)
                ),
                textColor = MaterialTheme.colors.semiDarkColor,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}

//Column(
//modifier = Modifier.fillMaxSize(),
//horizontalAlignment = Alignment.CenterHorizontally,
//verticalArrangement = Arrangement.Center
//) {
//
//    val activity = LocalContext.current as Activity
//
//    Text(text = "profile screen")
//
//    Button(onClick = { //farsi
//        dataStore.saveUserLanguage(PERSIAN_LANG)
//        activity.finish()
//        activity.startActivity(Intent(activity, MainActivity::class.java))
//    }) {
//
//        Text(text = "Fa")
//    }
//
//    Button(onClick = {  //english
//        dataStore.saveUserLanguage(ENGLISH_LANG)
//
//        activity.finish()
//        activity.startActivity(Intent(activity, MainActivity::class.java))
//    }) {
//
//        Text(text = "En")
//    }
//
//}