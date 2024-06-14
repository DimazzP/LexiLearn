package com.example.lexilearn.ui.views.pLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.GradientLogin
import com.example.lexilearn.ui.components.LoginTextButton
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.ctransTextWhite

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {

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
                onClick = { navController.navigate("home") },
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
            LottieProgressDialog(isDialogOpen = viewModel.isDialogOpen) {
                // This will be called when dialog is dismissed
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
