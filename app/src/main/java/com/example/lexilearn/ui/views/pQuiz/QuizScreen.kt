package com.example.lexilearn.ui.views.pQuiz

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.cGreen
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.ui.theme.cwhite
import com.example.lexilearn.util.ConstVar
import com.example.lexilearn.util.parseToQuizQuestion
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = koinViewModel()
) {
    val quiz = viewModel.quiz.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getQuiz()
    }

    LaunchedEffect(quiz.value) {
        quiz.value?.let {
            when (it) {
                ApiResponse.Loading -> viewModel.showLoading = true
                is ApiResponse.Success -> viewModel.showLoading = false
                is ApiResponse.Error -> {
                    viewModel.showLoading = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (buttonRef) = createRefs()

        GradientQuiz(
            navController = navController,
            headerText = stringResource(id = R.string.spelltitle),
            modifier = Modifier.fillMaxSize()
        ) {
            quiz.value?.let {
                when (it) {
                    is ApiResponse.Loading -> {}
                    is ApiResponse.Success -> {
                        val quiz = it.data.get(viewModel.quizState)
                        val type = quiz.type

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Text(
                                    "${viewModel.quizState + 1}/${it.data.size}",
                                    modifier = Modifier.padding(22.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    color = ctextWhite
                                )
                            }

                            when (type) {
                                "text" -> {
                                    ScreenText(
                                        quiz = quiz
                                    )
                                }

                                "audio" -> {
                                    ScreenAudio(
                                        quiz = quiz
                                    )
                                }
                            }
                        }

                        ButtonNext(
                            onclick = {
                                val lastQuiz = viewModel.quizState == it.data.size - 1
                                if (quiz.question.parseToQuizQuestion()
                                        .count { it == "?" } > viewModel.selectedChoice.count()
                                ) {
                                    Toast.makeText(
                                        context,
                                        "Please answer the question",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@ButtonNext
                                } else {
                                    viewModel.nextQuiz(
                                        questionId = quiz.id,
                                        choice = viewModel.selectedChoice,
                                        send = lastQuiz
                                    )
                                    if (lastQuiz) {
                                        navController.navigate("home") {
                                            popUpTo("quiz") { inclusive = true }
                                        }
                                    }
                                }
                            },
                            text = stringResource(if (viewModel.quizState == it.data.size - 1) R.string.submit else R.string.next),
                            painter = painterResource(id = R.drawable.ic_next),
                            modifier = Modifier
                                .padding(vertical = 30.dp, horizontal = 50.dp)
                                .fillMaxWidth()
                                .constrainAs(buttonRef) {
                                    bottom.linkTo(parent.bottom, margin = 12.dp)
                                }
                        )
                    }

                    is ApiResponse.Error -> {}
                }
            }

            LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
                viewModel.showLoading = false
            }
        }
    }
}

@Composable
fun ScreenText(quiz: QuizModel, viewModel: QuizViewModel = koinViewModel()) {
    val question = quiz.question.parseToQuizQuestion()
    val questionChoiceCounter = question.count { it == "?" }
    val choices = quiz.choices

    val selectedChoice = viewModel.selectedChoice
    val selectedChoiceCounter = selectedChoice.count()

    MyShadowCard(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = question.joinToString(" "),
                    color = ctextBlack,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = stringResource(
            R.string.answered_quiz_counter,
            selectedChoiceCounter,
            questionChoiceCounter
        ),
        modifier = Modifier
            .padding(12.dp),
        color = ctextWhite,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    HorizontalLine()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        choices.mapIndexed { index, item ->
            MyShadowCard(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (selectedChoice.contains(item)) {
                            selectedChoice.remove(item)
                        } else {
                            if (selectedChoiceCounter < questionChoiceCounter) {
                                selectedChoice.add(item)
                            }
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item,
                        color = ctextBlack,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    if (selectedChoice.contains(item)) {
                        Box(
                            modifier = Modifier
                                .background(ctextBlack, shape = RoundedCornerShape(16.dp))
                                .padding(8.dp)
                        ) {
                            val index = selectedChoice.indexOf(item)
                            Text(
                                text = "${index + 1}",
                                color = ctextWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenAudio(quiz: QuizModel, viewModel: QuizViewModel = koinViewModel()) {
    val question = quiz.question
    val choices = quiz.choices

    val selectedChoice = viewModel.selectedChoice
    val selectedChoiceCounter = selectedChoice.count()

    MyShadowCard(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center // Center horizontally
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .background(cGreen, shape = RoundedCornerShape(16.dp))
                            .padding(8.dp)
                            .clickable {
                                viewModel.playSound(question, ConstVar.SPEED_1)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_volume),
                            contentDescription = "Play",
                            tint = ctextWhite,
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                        )

                        Text(
                            text = "Normal",
                            color = ctextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.BottomCenter) // Align text at the bottom center
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .background(cGreen, shape = RoundedCornerShape(16.dp))
                            .padding(8.dp)
                            .clickable {
                                viewModel.playSound(question, ConstVar.SPEED_0_5)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_volume),
                            contentDescription = "Play",
                            tint = ctextWhite,
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                        )

                        Text(
                            text = "Slow",
                            color = ctextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.BottomCenter) // Align text at the bottom center
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = stringResource(
            R.string.answered_quiz_counter,
            selectedChoiceCounter,
            choices.size
        ),
        modifier = Modifier
            .padding(12.dp),
        color = ctextWhite,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    HorizontalLine()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        choices.mapIndexed { index, item ->
            MyShadowCard(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (selectedChoice.contains(item)) {
                            selectedChoice.remove(item)
                        } else {
                            if (selectedChoiceCounter < choices.size) {
                                selectedChoice.add(item)
                            }
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item,
                        color = ctextBlack,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    if (selectedChoice.contains(item)) {
                        Box(
                            modifier = Modifier
                                .background(ctextBlack, shape = RoundedCornerShape(16.dp))
                                .padding(8.dp)
                        ) {
                            val index = selectedChoice.indexOf(item)
                            Text(
                                text = "${index + 1}",
                                color = ctextWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(navController = rememberNavController())
}