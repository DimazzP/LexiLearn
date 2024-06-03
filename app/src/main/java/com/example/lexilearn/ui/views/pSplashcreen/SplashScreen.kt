package com.example.lexilearn.ui.views.pSplashcreen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.lexilearn.ui.components.GradientSplash
import com.example.lexilearn.ui.theme.ctextGray
import com.example.lexilearn.ui.theme.ctextWhite

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate("login"){
                popUpTo("splash") { inclusive = true }
            }
        }, 500) // Delay for 3 seconds
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
