package com.example.lexilearn.ui.views.pProfile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
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
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.di.KoinModules
import com.example.lexilearn.ui.components.CircleAvatar
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.NameTextField
import com.example.lexilearn.ui.components.PasswordTextField
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.cbackground
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextGray
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.ui.theme.cwhite
import com.example.lexilearn.util.PreferenceManager
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel(),
    preferenceManager: PreferenceManager = koinInject()
) {
    val updateProfileState = viewModel.updateProfileState.observeAsState()
    val changePasswordState = viewModel.changePasswordState.observeAsState()
    val context = LocalContext.current
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
    val currentDate = LocalDate.now()

    LaunchedEffect(Unit) {
        viewModel.displayName = preferenceManager.getName
    }

    LaunchedEffect(changePasswordState.value) {
        changePasswordState.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading = true
                is ApiResponse.Success -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, "Kata Sandi Telah Diubah", Toast.LENGTH_SHORT).show()
                }

                is ApiResponse.Error -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(updateProfileState.value) {
        updateProfileState.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading = true
                is ApiResponse.Success -> {
                    viewModel.showLoading = false
                    viewModel.displayName = viewModel.name.text
                    Toast.makeText(context, "Berhasil Mengubah Profile", Toast.LENGTH_SHORT).show()
                }

                is ApiResponse.Error -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
                text = viewModel.displayName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = ctextWhite,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(circleRef.top)
                    start.linkTo(circleRef.end, margin = 12.dp)
                }
            )
            Text(
                text = formatter.format(currentDate),
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
                    onClick = { viewModel.showProfile = true },
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
                    onClick = { viewModel.showProfile = false },
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
                            contentDescription = stringResource(id = R.string.strdefault),
                            modifier = Modifier.rotate(45f)
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
                .fillMaxSize()
        ) {
            if (viewModel.showProfile) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (nameRef, passwordRef, changeRef, logoutRef) = createRefs()
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

                    PasswordTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.password = it },
                        modifier = Modifier.constrainAs(passwordRef) {
                            top.linkTo(nameRef.bottom, margin = 12.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        })

                    CustomButton(
                        text = stringResource(id = R.string.btn_change_profile),
                        onClick = { viewModel.updateProfile() },
                        modifier = Modifier.constrainAs(changeRef) {
                            top.linkTo(passwordRef.bottom, margin = 24.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        })

                    Button(
                        onClick = ({
                            preferenceManager.clearAllPreferences()
                            navController.navigate("login") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ctextGray // Background color
                        ),
                        modifier = Modifier
                            .border(
                                2.dp,
                                ctextGray,
                                RoundedCornerShape(12.dp)
                            ) // Border color and shape
                            .background(
                                ctextGray,
                                RoundedCornerShape(12.dp)
                            ) // Background color and shape
                            .constrainAs(logoutRef) {
                                bottom.linkTo(parent.bottom, margin = 30.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = stringResource(
                                id = R.string.strdefault
                            ),
                            modifier = Modifier.padding(end = 4.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.btn_logout),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = ctextWhite
                        )
                    }
                }
            } else {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (current_passwordRef, new_passwordRef, confirm_passwordRef, changeRef, logoutRef) = createRefs()

                    PasswordTextField(
                        value = viewModel.current_password,
                        onValueChange = { viewModel.current_password = it },
                        modifier = Modifier.constrainAs(current_passwordRef) {
                            top.linkTo(parent.top, margin = 12.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        },
                        textPassword = stringResource(id = R.string.current_password)
                    )

                    PasswordTextField(
                        value = viewModel.new_password,
                        onValueChange = { viewModel.new_password = it },
                        modifier = Modifier.constrainAs(new_passwordRef) {
                            top.linkTo(current_passwordRef.bottom, margin = 12.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        },
                        textPassword = stringResource(id = R.string.new_password)
                    )

                    PasswordTextField(
                        value = viewModel.confirm_password,
                        onValueChange = { viewModel.confirm_password = it },
                        modifier = Modifier.constrainAs(confirm_passwordRef) {
                            top.linkTo(new_passwordRef.bottom, margin = 12.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        },
                        textPassword = stringResource(id = R.string.confirm_password)
                    )

                    CustomButton(
                        text = stringResource(id = R.string.btn_change_password),
                        onClick = { viewModel.changePassword() },
                        modifier = Modifier.constrainAs(changeRef) {
                            top.linkTo(confirm_passwordRef.bottom, margin = 24.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                            start.linkTo(parent.start, margin = 12.dp)
                            width = Dimension.fillToConstraints
                        })
                    Button(
                        onClick = ({
                            preferenceManager.clearAllPreferences()
                            KoinModules.reloadModule()

                            navController.popBackStack()

                            navController.navigate("login") {
                                popUpTo("profile") { inclusive = true }
                            }
                        }),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ctextGray // Background color
                        ),
                        modifier = Modifier
                            .border(
                                2.dp,
                                ctextGray,
                                RoundedCornerShape(12.dp)
                            ) // Border color and shape
                            .background(
                                ctextGray,
                                RoundedCornerShape(12.dp)
                            ) // Background color and shape
                            .constrainAs(logoutRef) {
                                bottom.linkTo(parent.bottom, margin = 30.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = stringResource(
                                id = R.string.strdefault
                            ),
                            modifier = Modifier.padding(end = 4.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.btn_logout),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = ctextWhite
                        )
                    }
                }

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
