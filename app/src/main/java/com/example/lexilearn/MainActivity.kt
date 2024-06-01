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
import com.example.lexilearn.ui.views.pQuiz.pSpell.SpellScreen
import com.example.lexilearn.ui.views.pQuiz.pWrite.WriteScreen
import com.example.lexilearn.ui.views.pRegister.RegisterScreen
import com.example.lexilearn.ui.views.pResultScreening.ResultScreeningScreen
import com.example.lexilearn.ui.views.pScreening.ScreeningScreen
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
    NavHost(navController = navController, startDestination = "home") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("read"){ ReadScreen(navController) }
        composable("spell"){ SpellScreen(navController) }
        composable("write"){ WriteScreen(navController) }
        composable("screening"){ ScreeningScreen(navController) }
        composable("resultscreening"){ ResultScreeningScreen(navController) }
    }
}