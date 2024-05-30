package com.example.lexilearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.ui.views.pHome.HomeScreen
import com.example.lexilearn.ui.views.pLogin.LoginScreen
//import com.example.lexilearn.ui.views.pQuiz.pRead.DragAndDropExample
import com.example.lexilearn.ui.views.pQuiz.pRead.ReadScreen
import com.example.lexilearn.ui.views.pRegister.RegisterScreen
import com.example.lexilearn.ui.views.pSplashcreen.SplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "read") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("read"){ ReadScreen(navController) }
    }
}