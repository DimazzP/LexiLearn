package com.example.lexilearn.ui.views.pQuiz

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.DraggableAnswerCard
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.ui.views.pQuiz.pRead.ReadScreen
import com.example.lexilearn.ui.views.pQuiz.pRead.ReadViewModel
import com.example.lexilearn.util.parseToQuizQuestion
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = koinViewModel(),
    readViewModel: ReadViewModel = koinViewModel()
) {
    val quiz = viewModel.quiz.observeAsState()
    val context = LocalContext.current

    val maxWidthR = 280.dp
    val maxHeightR = 60.dp

    val minWidthR = 90.dp
    val minHeightR = 40.dp

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

    GradientQuiz(
        navController = navController,
        headerText = stringResource(id = R.string.spelltitle),
        modifier = Modifier.fillMaxSize()
    ) {
        quiz.value?.let {
            when(it) {
                is ApiResponse.Loading -> {}
                is ApiResponse.Success -> {
                    val type = it.data.type
                    when (type) {
                        "text" -> {
                            readViewModel.getQuiz(it.data)
                            ReadScreen()
                        }
                    }
                }
                is ApiResponse.Error -> {}
            }
        }
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End,
//            ) {
//                Text(
//                    "",
//                    modifier = Modifier.padding(22.dp),
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 18.sp,
//                    color = ctextWhite
//                )
//            }
//
//            quiz.value?.let {
//                when (it) {
//                    is ApiResponse.Success -> {
//                        val type = it.data.type
//
//                        when (type) {
//                            "text" -> {
//                                val question = it.data.question.parseToQuizQuestion()
//                                MyShadowCard(
//                                    modifier = Modifier
//                                        .padding(12.dp)
//                                        .fillMaxWidth()
//                                ) {
//                                    Row(modifier = Modifier.padding(12.dp)) {
//                                        question.map {
//                                            Log.d("QuizScreen", "it: $it")
//                                            if (it === "?") {
//                                                CardQuiz(
//                                                    modifier = Modifier
//                                                        .padding(vertical = 10.dp)
//                                                        .width(minWidthR)
//                                                        .height(minHeightR)
//                                                ) {
//                                                    Text(
//                                                        text = it, // Use the state to display text
//                                                        color = ctextWhite,
//                                                        fontWeight = FontWeight.Bold,
//                                                        textAlign = TextAlign.Center,
//                                                        modifier = Modifier.fillMaxWidth()
//                                                    )
//                                                }
//                                            } else {
//                                                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
//                                                    Text(
//                                                        text = it,
//                                                        color = ctextBlack,
//                                                        fontSize = 20.sp,
//                                                        fontWeight = FontWeight.Bold,
//                                                        textAlign = TextAlign.Center,
//                                                    )
//                                                }
//                                            }
//
//                                        }
//                                    }
//                                }
//
//                                Spacer(modifier = Modifier.height(40.dp))
//                                HorizontalLine()
//                                Spacer(modifier = Modifier.height(40.dp))
//
//                                Column(
//                                    modifier = Modifier
//                                        .fillMaxSize(),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center
//                                ) {
//                                    val answerList = it.data.answerList
//                                    Log.d("QuizScreen", "answerList: $answerList")
//
//                                    answerList.mapIndexed { index, item ->
//                                        Card(
//                                            modifier = Modifier
//                                                .padding(12.dp)
//                                                .padding(12.dp),
//                                            onClick = {
//                                                Log.d("QuizScreen", "item: $item")
//                                            },
//                                        ) {
//                                            Text(text = item)
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    is ApiResponse.Error -> {}
//                    ApiResponse.Loading -> {}
//                }

//                ButtonNext(
//                    onclick = {
//                        navController.navigate("write")
//                    },
//                    text = stringResource(id = R.string.next),
//                    painter = painterResource(id = R.drawable.ic_next),
//                    modifier = Modifier
//                        .padding(vertical = 30.dp, horizontal = 50.dp)
//                        .fillMaxWidth()
//                )
//            }

        LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
            viewModel.showLoading = false
        }
    }
}

//@Composable
//fun TextScreen(viewModel: QuizViewModel= koinViewModel()) {
//    MyShadowCard(
//        modifier = Modifier
//            .padding(12.dp)
//            .fillMaxWidth()
//    ) {
//        FlowRow(
//            modifier = Modifier.padding(12.dp),
//        ) {
//            viewModel.dataQuiz.forEach { dt ->
//                val id = dt.id
//                if (!viewModel.boxRectDragable.containsKey(id))
//                    viewModel.boxRectDragable[id] = Rect.Zero
//                if (!viewModel.boxRectQuiz.containsKey(id))
//                    viewModel.boxRectQuiz[id] = Rect.Zero
//                if (!viewModel.quizXOffset.containsKey(id))
//                    viewModel.quizXOffset[id] = 0f
//                if (!viewModel.quizYOffset.containsKey(id))
//                    viewModel.quizYOffset[id] = 0f
//                if (dt.type) {
//                    CardQuiz(
//                        modifier = Modifier
//                            .padding(vertical = 10.dp)
//                            .width(minWidthR)
//                            .height(minHeightR)
//                            .onGloballyPositioned { coordinates ->
//                                viewModel.boxRectQuiz[id] = coordinates.boundsInWindow()
//                            }
//                    ) {
//                        Text(
//                            text = dt.data, // Use the state to display text
//                            color = ctextWhite,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        if (dt.showCard) {
//                            DraggableAnswerCard(
//                                item = dt.data,
//                                modifier = Modifier
//                                    .offset {
//                                        val xOffset = viewModel.quizXOffset[id] ?: 0f
//                                        val yOffset = viewModel.quizYOffset[id] ?: 0f
//                                        IntOffset(
//                                            xOffset.roundToInt(),
//                                            yOffset.roundToInt()
//                                        )
//                                    }
//                                    .onGloballyPositioned {
//                                        viewModel.boxRectDragable[id] = it.boundsInWindow()
//                                    }
//                                    .fillMaxSize()
//                                    .pointerInput(Unit) {
//                                        detectDragGestures(
//                                            onDrag = { change, dragAmount ->
//                                                change.consume()
//                                                viewModel.quizXOffset[id] =
//                                                    viewModel.quizXOffset[id]!! + dragAmount.x
//                                                viewModel.quizYOffset[id] =
//                                                    viewModel.quizYOffset[id]!! + dragAmount.y
//                                            },
//                                            onDragEnd = {
//                                                var checkNull = false
//                                                for ((ind, entry) in viewModel.boxRectQuiz.entries.withIndex()) {
//                                                    val (key, rect) = entry
//                                                    if (key == id)
//                                                        continue
//
//                                                    if (viewModel.dataQuiz[ind].hasContent)
//                                                        continue
//
//                                                    if (viewModel.boxRectDragable[id]!!.overlaps(
//                                                            rect
//                                                        )
//                                                    ) {
//                                                        viewModel.dataQuiz =
//                                                            viewModel.dataQuiz.apply {
//                                                                this[ind] = this[ind].copy(
//                                                                    data = dt.data,
//                                                                    showCard = true,
//                                                                    emp = dt.emp,
//                                                                    hasContent = true
//                                                                )
//                                                            }
//                                                        dt.apply {
//                                                            hasContent = false
//                                                            showCard = false
//                                                            data = "?"
//                                                        }
//                                                        checkNull = true
//                                                        viewModel.quizXOffset[id] = 0f
//                                                        viewModel.quizYOffset[id] = 0f
//                                                        break
//                                                    }
//
//                                                }
//                                                if (viewModel.boxRectDragable[id]!!.overlaps(
//                                                        viewModel.rectColumnAnswer.value
//                                                    )
//                                                ) {
//                                                    val emDt = dt.emp
//                                                    if (emDt != null) {
//                                                        dt.apply {
//                                                            hasContent = false
//                                                            showCard = false
//                                                            data = "?"
//                                                        }
//                                                        checkNull = true
//                                                        viewModel.cardWidth[emDt] = maxWidthR
//                                                        viewModel.cardHeight[emDt] = maxHeightR
//                                                        viewModel.quizXOffset[id] = 0f
//                                                        viewModel.quizYOffset[id] = 0f
//                                                        dt.data = "?"
//                                                    }
//                                                }
//                                                if (!checkNull) {
//                                                    viewModel.quizXOffset[id] = 0f
//                                                    viewModel.quizYOffset[id] = 0f
//                                                }
//                                            }
//                                        )
//                                    }
//                            )
//                        }
//                    }
//                } else {
//                    Box(modifier = Modifier.align(Alignment.CenterVertically)) {
//                        Text(
//                            text = dt.data,
//                            color = ctextBlack,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}

@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(navController = rememberNavController())
}