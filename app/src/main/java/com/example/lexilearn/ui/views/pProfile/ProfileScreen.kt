package com.example.lexilearn.ui.views.pProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.lexilearn.ui.components.CircleAvatar
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.EmailTextField
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.NameTextField
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.cbackground
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(cbackground)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(cprimary)
        ) {
            val (backRef, topRef, circleRef, titleRef, dateRef, rowRef) = createRefs()
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.constrainAs(backRef) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = ctextWhite
                )
            }

            Text(
                text = "Profile",
                color = ctextWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(topRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(backRef.top)
                    bottom.linkTo(backRef.bottom)
                })

            CircleAvatar(
                imageResId = R.drawable.ic_user,
                modifier = Modifier.constrainAs(circleRef) {
                    top.linkTo(backRef.bottom, margin = 14.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
            Text(
                text = "Name",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = ctextWhite,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(circleRef.top)
                    start.linkTo(circleRef.end, margin = 12.dp)
                }
            )
            Text(
                text = "Saturday, 25 May 2024",
                fontSize = 14.sp,
                color = ctextWhite,
                modifier = Modifier.constrainAs(dateRef) {
                    top.linkTo(titleRef.bottom, margin = 4.dp)
                    start.linkTo(titleRef.start)
                }
            )
            Row(modifier = Modifier.constrainAs(rowRef) {
                top.linkTo(circleRef.bottom, margin = 12.dp)
                start.linkTo(circleRef.start)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = caccent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(
                        vertical = 4.dp,
                        horizontal = 12.dp
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            tint = ctextBlack,
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = stringResource(id = R.string.strdefault)
                        )
                        Text(text = stringResource(id = R.string.profile), color = ctextBlack)
                    }
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = cwhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(
                        vertical = 4.dp,
                        horizontal = 12.dp
                    ),
                    modifier = Modifier.padding(start = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            tint = ctextBlack,
                            painter = painterResource(id = R.drawable.ic_key),
                            contentDescription = stringResource(id = R.string.strdefault)
                        )
                        Text(
                            text = stringResource(id = R.string.change_password),
                            color = ctextBlack
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (nameRef, emailRef, passwordRef, changeRef) = createRefs()
                NameTextField(
                    placeholder = stringResource(id = R.string.fullname),
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    ic = R.drawable.ic_user,
                    modifier = Modifier.constrainAs(nameRef) {
                        end.linkTo(parent.end, margin = 12.dp)
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        width = Dimension.fillToConstraints
                    }
                )
                EmailTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    modifier = Modifier.constrainAs(emailRef) {
                        top.linkTo(nameRef.bottom, margin = 12.dp)
                        end.linkTo(parent.end, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        width = Dimension.fillToConstraints
                    })

                PasswordTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    modifier = Modifier.constrainAs(passwordRef) {
                        top.linkTo(emailRef.bottom, margin = 12.dp)
                        end.linkTo(parent.end, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        width = Dimension.fillToConstraints
                    })

                CustomButton(
                    text = stringResource(id = R.string.btn_change),
                    onClick = { },
                    modifier = Modifier.constrainAs(changeRef) {
                        top.linkTo(passwordRef.bottom, margin = 12.dp)
                        end.linkTo(parent.end, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        width = Dimension.fillToConstraints
                    })
            }
        }
        LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
            viewModel.showLoading = false
        }
    }
}
@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
