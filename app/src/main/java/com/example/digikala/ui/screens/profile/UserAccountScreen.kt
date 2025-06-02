package com.example.digikala.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.profile.SetUserNameRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.CursorColor
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants
import com.example.digikala.util.Constants.USER_NAME
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest


@Composable
fun UserAccountScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    var name = ""
    var family = ""
    if (USER_NAME != "null") {
        val parts = USER_NAME.split(" - ")
        name = parts[0]
        family = parts[1]
    }
    var firstName by remember { mutableStateOf(name) }
    var lastName by remember { mutableStateOf(family) }

    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.setUserNameResult.collectLatest { setUserNameResponse ->
            when (setUserNameResponse) {
                is NetworkResult.Success -> {
                    dataStoreViewModel.saveUserName("$firstName - $lastName")
                    USER_NAME = "$firstName - $lastName"
                    navController.popBackStack()
                }
                is NetworkResult.Error -> {
                    profileViewModel.loadingState = false
                    Log.e("3636", "setUserNameResponse error : ${setUserNameResponse.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }


    Column {

        UserAccountHeader(navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.enter_name_lastname),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = stringResource(id = R.string.firstname),
                    style = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    shape = MaterialTheme.roundedShape.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.searchBarBg,
                        focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                        unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                        cursorColor = MaterialTheme.colors.CursorColor,
                    )
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = stringResource(id = R.string.lastname),
                    style = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    shape = MaterialTheme.roundedShape.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.searchBarBg,
                        focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                        unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                        cursorColor = MaterialTheme.colors.CursorColor,
                    )
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = MaterialTheme.spacing.biggerSmall,
                        vertical = MaterialTheme.spacing.small
                    ),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        profileViewModel.setUserName(
                            SetUserNameRequest(
                                token = Constants.USER_TOKEN,
                                name = "$firstName - $lastName"
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                    shape = MaterialTheme.roundedShape.small
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.confirm_information),
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
private fun UserAccountHeader(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconButton(onClick = { navController.navigate(Screen.Profile.route) }
                ) {
                    Icon(
                        Icons.Filled.ArrowForward, contentDescription = "Back",
                        tint = MaterialTheme.colors.selectedBottomBar
                    )
                }

                Text(
                    text = stringResource(id = R.string.user_information),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.extraSmall)
                .background(MaterialTheme.colors.searchBarBg)
                .fillMaxWidth()
        )
    }
}

