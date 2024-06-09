package com.example.lexilearn.ui.views.pRegister

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.GradientRegister
import com.example.lexilearn.ui.components.LoginTextButton
import com.example.lexilearn.ui.components.NameTextField
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.ctransTextWhite
import kotlinx.coroutines.flow.forEach


@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = viewModel()) {

    GradientRegister {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (txtTitle, txtDesc, nameRef, emailRef, passwordRef, registerRef, loginRef) = createRefs()
            Text(
                text = stringResource(id = R.string.regissignup),
                color = ctransTextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(txtTitle) {
                    bottom.linkTo(txtDesc.top, margin = 6.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = stringResource(id = R.string.regisdesc),
                color = ctransTextWhite,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txtDesc) {
                    bottom.linkTo(nameRef.top, margin = 44.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )
            NameTextField(
                placeholder = stringResource(id = R.string.fullname),
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                ic = R.drawable.ic_user,
                modifier = Modifier.constrainAs(nameRef) {
                    bottom.linkTo(emailRef.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            EmailTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                modifier = Modifier.constrainAs(emailRef) {
                    bottom.linkTo(passwordRef.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            PasswordTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                modifier = Modifier.constrainAs(passwordRef) {
                    bottom.linkTo(registerRef.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            CustomButton(
                text = stringResource(id = R.string.regis),
                onClick = { },
                modifier = Modifier.constrainAs(registerRef) {
                    bottom.linkTo(loginRef.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            LoginTextButton(
                textHelper = stringResource(id = R.string.regishave) + " ",
                textBtn = stringResource(id = R.string.login),
                onclick = {
                    navController.popBackStack()
                },
                modifier = Modifier.constrainAs(loginRef) {
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    width = Dimension.wrapContent
                }
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val viewModel: RegisterViewModel = viewModel()
    RegisterScreen(navController = navController, viewModel = viewModel)
}

