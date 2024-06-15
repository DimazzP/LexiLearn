package com.example.lexilearn.ui.views.pRegister

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.GradientRegister
import com.example.lexilearn.ui.components.LoginTextButton
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.NameTextField
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.ctransTextWhite
import com.example.lexilearn.util.isValidEmail
import com.example.lexilearn.util.isValidName
import com.example.lexilearn.util.isValidPassword
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {
    val registerState = viewModel.registerState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(registerState.value) {
        registerState.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading = true
                is ApiResponse.Success -> {
                    Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
                is ApiResponse.Error -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
                onClick = {
                    try {
                        viewModel.name.isValidName(context)
                        viewModel.email.isValidEmail(context)
                        viewModel.password.isValidPassword(context)

                        viewModel.register()
                    } catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = registerState.value !== ApiResponse.Loading,
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
            LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
                viewModel.showLoading = false
            }
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

