package com.example.lexilearn.ui.views.pScreening

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.screening.model.ScreeningQuestionModel
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardScreening
import com.example.lexilearn.ui.components.GradientScreening
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.theme.ctextGray
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScreeningScreen(navController: NavController, viewModel: ScreeningViewModel = koinViewModel()) {
    val screeningQuestions = viewModel.screeningQuestions.observeAsState()
    val screeningAnswerResult = viewModel.screeningAnswerResult.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getQuestions()
    }

    LaunchedEffect(screeningAnswerResult.value) {
        screeningAnswerResult.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading.value = true
                is ApiResponse.Success -> {
                    viewModel.showLoading.value = false
                    navController.navigate("resultscreening/${it.data.score}") {
                        popUpTo("screening") { inclusive = true }
                    }
                }
                is ApiResponse.Error -> {
                    viewModel.showLoading.value = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(screeningQuestions.value) {
        screeningQuestions.value?.let {
            when (it) {
                is ApiResponse.Loading -> viewModel.showLoading.value = true
                is ApiResponse.Success -> viewModel.showLoading.value = false
                is ApiResponse.Error -> {
                    viewModel.showLoading.value = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    GradientScreening(
        backButton = { navController.popBackStack() },
        headerText = stringResource(id = R.string.screentitle),
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            val (lineRef, lazyRef, buttonRef) = createRefs()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(ctextGray)
                .constrainAs(lineRef) {
                    top.linkTo(parent.top)
                })

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.constrainAs(lazyRef) {
                    top.linkTo(lineRef.bottom)
                }) {
                if (screeningQuestions.value is ApiResponse.Success) {
                    itemsIndexed(((screeningQuestions.value as ApiResponse.Success<List<ScreeningQuestionModel>>).data)) { index, item ->
                        CardScreening(
                            question = "${index + 1}. ${item.question}",
                            selectedOption = item.answer,
                            onOptionSelected = { selectOption ->
                                item.answer = selectOption
                            })
                    }
                    item {
                        ButtonNext(
                            onclick = {
                                val questions = (screeningQuestions.value as ApiResponse.Success<List<ScreeningQuestionModel>>).data
                                if(questions.all { it.answer.isNotEmpty() }) {
                                    viewModel.sendAnswers()
                                } else {
                                    Toast.makeText(context, context.getString(R.string.please_answer_all_questions), Toast.LENGTH_SHORT).show()
                                }
                            },
                            text = stringResource(id = R.string.screensend),
                            painter = painterResource(id = R.drawable.ic_send),
                            modifier = Modifier
                                .padding(vertical = 30.dp, horizontal = 50.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
            Box(modifier = Modifier
                .padding(20.dp)
                .constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
            }
            LottieProgressDialog(isDialogOpen = viewModel.showLoading.value) {
                viewModel.showLoading.value = false
            }
        }
    }
}

@Preview
@Composable
fun ScreeningScreenPreview() {
    val navController = rememberNavController()
    val viewModel: ScreeningViewModel = viewModel()
    ScreeningScreen(navController = navController, viewModel = viewModel)
}
