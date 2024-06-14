package com.example.lexilearn.ui.views.pSplashcreen

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.di.KoinModules
import com.example.lexilearn.ui.components.GradientSplash
import com.example.lexilearn.ui.theme.ctextGray
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.util.PreferenceManager
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun SplashScreen(navController: NavController, preferenceManager: PreferenceManager = koinInject(), viewModel: SplashViewModel = koinViewModel()) {
    val state = viewModel.state.observeAsState()

    LaunchedEffect(state.value) {
        val token = preferenceManager.getToken

        if (token.isNotEmpty()) {
            viewModel.inspect()

            state.value?.let {
                when (it) {
                    ApiResponse.Loading -> Log.d("SplashScreen", "Loading")
                    is ApiResponse.Success -> {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }

                    is ApiResponse.Error -> {
                        Log.d("SplashScreen", it.errorMessage)
                        navController.navigate("login") {
                            popUpTo("splash") { inclusive = true }
                        }
                        preferenceManager.clearAllPreferences()
                        KoinModules.reloadModule()
                    }
                }

            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    GradientSplash {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, title, byText, author) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.img_logo), // Replace with your logo drawable resource
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(150.dp) // Adjust the size as needed
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 50.dp)
                    }
            )

            Text(
                text = "LexiLearn",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ctextWhite, // Using color from theme
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = "by",
                fontSize = 16.sp,
                color = ctextGray, // Using color from theme
                modifier = Modifier.constrainAs(byText) {
                    bottom.linkTo(author.top, margin = 4.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = "MPUS BEKERJA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ctextWhite, // Using color from theme
                modifier = Modifier.constrainAs(author) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
