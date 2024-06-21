package com.example.lexilearn

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lexilearn.data.news.model.deserializeArticle
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.ui.views.pAlphabet.AlphabetScreen
import com.example.lexilearn.ui.views.pDetailNews.NewsDetailScreen
import com.example.lexilearn.ui.views.pHome.HomeScreen
import com.example.lexilearn.ui.views.pLogin.LoginScreen
import com.example.lexilearn.ui.views.pNews.NewsScreen
import com.example.lexilearn.ui.views.pProfile.ProfileScreen
import com.example.lexilearn.ui.views.pQuiz.QuizScreen
//import com.example.lexilearn.ui.views.pQuiz.pRead.DragAndDropExample
import com.example.lexilearn.ui.views.pRegister.RegisterScreen
import com.example.lexilearn.ui.views.pResultQuiz.ResultQuizScreen
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
fun MyApp(sharedViewModel: SharedViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("quiz") { QuizScreen(navController)}
        composable("news") { NewsScreen(navController, sharedViewModel) }
        composable("detail_news") { NewsDetailScreen(navController, sharedViewModel) }
        composable(
            "resultquiz/{score}/{quiz}",
            listOf(
                navArgument("score") {
                    type = NavType.StringType
                },
                navArgument("quiz") {
                    type = NavType.StringType
                }
            )
        ) {
            val score = it.arguments?.getString("score")
            val quiz = it.arguments?.getString("quiz")
            ResultQuizScreen(navController, score!!, quiz!!)
        }
        composable("screening"){ ScreeningScreen(navController) }
        composable(
            "resultscreening/{result}",
            listOf(
                navArgument("result") {
                    type = NavType.IntType
                }
            )
        ) {
            val result = it.arguments?.getInt("result")
            ResultScreeningScreen(navController, result!!)
        }
        composable("alphabet") { AlphabetScreen(navController)}
        composable("profile") { ProfileScreen(navController) }
    }
}