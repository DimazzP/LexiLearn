package com.example.lexilearn.ui.views.pLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.GradientLogin
import com.example.lexilearn.ui.components.LoginTextButton
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.ui.theme.ctransTextWhite

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    GradientLogin {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (txtTitle, txtDesc, emailRef, passwordRef, loginButtonRef, registerTextRef) = createRefs()

            Text(
                text = "Login",
                color = ctransTextWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtTitle){
                    bottom.linkTo(txtDesc.top, margin = 6.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Login to Lexilearn and experience the ease of learning. Specially designed to help you overcome dyslexia with innovative features",
                color = ctransTextWhite,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txtDesc){
                    bottom.linkTo(emailRef.top, margin = 44.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            EmailTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.constrainAs(emailRef) {
                    bottom.linkTo(passwordRef.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.constrainAs(passwordRef) {
                    bottom.linkTo(loginButtonRef.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            CustomButton(
                text = "Sign In",
                onClick = { navController.navigate("home") },
                modifier = Modifier.constrainAs(loginButtonRef) {
                    bottom.linkTo(registerTextRef.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            LoginTextButton(
                textBtn = "Register",
                textHelper = "Do not have account? ",
                onclick = { navController.navigate("register") },
                modifier = Modifier.constrainAs(registerTextRef) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.wrapContent
                }
            )
        }
    }
}