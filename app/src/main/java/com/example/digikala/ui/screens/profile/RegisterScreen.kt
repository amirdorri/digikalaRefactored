package com.example.digikala.ui.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.InputValidation.isValidPassword
import com.example.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
  //  val loginResponse by viewModel.loginResponse.collectAsState()
    val context = LocalContext.current
  //  var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Dispatchers.Main) {
        viewModel.loginResponse.collectLatest { response ->
            when (response) {

                is NetworkResult.Success -> {

                    response.data?.let {
                        if(it.token.isNotEmpty())
                            viewModel.screenState = ProfileScreenState.PROFILE_STATE

                    }

                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                    viewModel.loadingState = false

                }

                is NetworkResult.Error -> {
                    viewModel.loadingState = false
                    Log.e("loginResponse", response.message.toString())
                }

                is NetworkResult.Loading -> {}

            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.digi_settings),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .size(MaterialTheme.spacing.semiLarge),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold
        )

        MyEditText(
            isReadOnly = true,
            value = viewModel.inputPhoneState,
            placeholder = stringResource(id = R.string.phone_and_email),
            onValueChange = {
                viewModel.inputPhoneState = it
            },
        )
        MyEditText(
            value = viewModel.inputPasswordState,
            placeholder = stringResource(id = R.string.set_password),
            onValueChange = {
                viewModel.inputPasswordState = it
            },
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (viewModel.loadingState){
            LoadingButton()
        } else {
            MyButton(text = stringResource(id = R.string.digikala_login)) {
                if (isValidPassword(viewModel.inputPasswordState)) {
                    viewModel.login()
                    //viewModel.screenState = ProfileScreenState.PROFILE_STATE

                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}