package com.example.lexilearn.ui.views.pLogin

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.GradientLogin
import com.example.lexilearn.ui.components.LoginTextButton
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.ctransTextWhite
import com.example.lexilearn.util.isValidEmail
import com.example.lexilearn.util.isValidPassword
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
    val loginState = viewModel.loginState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(loginState.value) {
        loginState.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading = true
                is ApiResponse.Success -> {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                is ApiResponse.Error -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    GradientLogin {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (txtTitle, txtDesc, emailRef, passwordRef, loginButtonRef, registerTextRef) = createRefs()

            Text(
                text = stringResource(id = R.string.login),
                color = ctransTextWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtTitle) {
                    bottom.linkTo(txtDesc.top, margin = 6.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = stringResource(id = R.string.logindesc),
                color = ctransTextWhite,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txtDesc) {
                    bottom.linkTo(emailRef.top, margin = 44.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            EmailTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                modifier = Modifier.constrainAs(emailRef) {
                    bottom.linkTo(passwordRef.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            PasswordTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                modifier = Modifier.constrainAs(passwordRef) {
                    bottom.linkTo(loginButtonRef.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            CustomButton(
                text = stringResource(id = R.string.login),
                onClick = {
                    try {
                        viewModel.email.isValidEmail(context)
                        viewModel.password.isValidPassword(context)

                        viewModel.login()
                    } catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = loginState.value !== ApiResponse.Loading,
                modifier = Modifier.constrainAs(loginButtonRef) {
                    bottom.linkTo(registerTextRef.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            LoginTextButton(
                textBtn = stringResource(id = R.string.regis),
                textHelper = stringResource(id = R.string.loginhave) + " ",
                onclick = {
                    navController.navigate("register")
                          },
                modifier = Modifier.constrainAs(registerTextRef) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.wrapContent
                }
            )
            LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
                viewModel.showLoading = false
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
