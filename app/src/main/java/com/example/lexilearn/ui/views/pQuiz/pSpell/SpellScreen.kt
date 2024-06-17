package com.example.lexilearn.ui.views.pQuiz.pSpell


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.DraggableAnswerCard
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextWhite
import kotlin.math.roundToInt
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.theme.ctextGray
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lexilearn.ui.components.LottieProgressDialog


@Composable
fun SpellScreen(navController: NavController, viewModel: SpellViewModel = viewModel()) {

    val maxSize = 120.dp

    val minSize = 70.dp


    Surface(modifier = Modifier.fillMaxSize()) {
        GradientQuiz(
            navController = navController,
            headerText = stringResource(id = R.string.spelltitle),
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout {
                val buttonRef = createRef()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            "Level: 3",
                            modifier = Modifier.padding(22.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = ctextWhite
                        )
                    }
                    MyShadowCard(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Susun Kata",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_news),
                                contentDescription = "image",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                viewModel.dataQuiz.forEach { dt ->
                                    val id = dt.id
                                    Log.d("dataQuizID", id.toString())
                                    if (!viewModel.boxRectDragable.containsKey(id))
                                        viewModel.boxRectDragable[id] = Rect.Zero
                                    if (!viewModel.boxRectQuiz.containsKey(id))
                                        viewModel.boxRectQuiz[id] = Rect.Zero
                                    if (!viewModel.quizXOffset.containsKey(id))
                                        viewModel.quizXOffset[id] = 0f
                                    if (!viewModel.quizYOffset.containsKey(id))
                                        viewModel.quizYOffset[id] = 0f
                                    CardQuiz(
                                        modifier = Modifier
                                            .padding(vertical = 10.dp)
                                            .size(minSize)
                                            .onGloballyPositioned { coordinates ->
                                                if (dt.type)
                                                    viewModel.boxRectQuiz[id] = coordinates.boundsInWindow()
                                            }
                                    ) {
                                        if (dt.type) {
                                            Text(
                                                text = dt.data, // Use the state to display text
                                                color = ctextWhite,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                            if (dt.showCard) {
                                                DraggableAnswerCard(
                                                    item = dt.data,
                                                    modifier = Modifier
                                                        .offset {
                                                            val xOffset = viewModel.quizXOffset[id] ?: 0f
                                                            val yOffset = viewModel.quizYOffset[id] ?: 0f
                                                            IntOffset(
                                                                xOffset.roundToInt(),
                                                                yOffset.roundToInt()
                                                            )
                                                        }
                                                        .onGloballyPositioned {
                                                            viewModel.boxRectDragable[id] =
                                                                it.boundsInWindow()
                                                        }
                                                        .fillMaxSize()
                                                        .pointerInput(Unit) {
                                                            detectDragGestures(
                                                                onDrag = { change, dragAmount ->
                                                                    change.consume()
                                                                    viewModel.quizXOffset[id] =
                                                                        viewModel.quizXOffset[id]!! + dragAmount.x
                                                                    viewModel.quizYOffset[id] =
                                                                        viewModel.quizYOffset[id]!! + dragAmount.y
                                                                },
                                                                onDragEnd = {
                                                                    var checkNull = false
                                                                    for ((ind, entry) in viewModel.boxRectQuiz.entries.withIndex()) {
                                                                        val (key, rect) = entry
                                                                        if (key == id)
                                                                            continue

                                                                        if (viewModel.dataQuiz[ind].hasContent)
                                                                            continue

                                                                        if (viewModel.boxRectDragable[id]!!.overlaps(
                                                                                rect
                                                                            )
                                                                        ) {
                                                                            viewModel.dataQuiz =
                                                                                viewModel.dataQuiz.apply {
                                                                                    this[ind] =
                                                                                        this[ind].copy(
                                                                                            data = dt.data,
                                                                                            showCard = true,
                                                                                            emp = dt.emp,
                                                                                            hasContent = true
                                                                                        )
                                                                                }
                                                                            dt.apply {
                                                                                hasContent = false
                                                                                showCard = false
                                                                                data = "?"
                                                                            }
                                                                            checkNull = true
                                                                            viewModel.quizXOffset[id] = 0f
                                                                            viewModel.quizYOffset[id] = 0f
                                                                            break
                                                                        }

                                                                    }
                                                                    if (viewModel.boxRectDragable[id]!!.overlaps(
                                                                            viewModel.rectColumnAnswer.value
                                                                        )
                                                                    ) {
                                                                        val emDt = dt.emp
                                                                        if (emDt != null) {
                                                                            dt.apply {
                                                                                hasContent = false
                                                                                showCard = false
                                                                                data = "?"
                                                                            }
                                                                            checkNull = true
                                                                            viewModel.cardSize[emDt] = maxSize
                                                                            viewModel.quizXOffset[id] = 0f
                                                                            viewModel.quizYOffset[id] = 0f
                                                                            dt.data = "?"
                                                                        }
                                                                    }
                                                                    if (!checkNull) {
                                                                        viewModel.quizXOffset[id] = 0f
                                                                        viewModel.quizYOffset[id] = 0f
                                                                    }
                                                                }
                                                            )
                                                        }
                                                )
                                            }
                                        } else {
                                            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                                Text(
                                                    text = dt.data,
                                                    color = ctextWhite,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center,
                                                )
                                            }

                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(ctextGray)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned {
                                viewModel.rectColumnAnswer.value = it.boundsInWindow()
                            },
                    ) {
                        viewModel.listAnswer.chunked(2).forEach { rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                rowItems.forEach { item ->
                                    val id = item.id
                                    if (!viewModel.cardSize.containsKey(id))
                                        viewModel.cardSize[id] = maxSize
                                    if (!viewModel.boxRectAnswer.containsKey(id))
                                        viewModel.boxRectAnswer[id] = Rect.Zero
                                    if (!viewModel.answerXOffset.containsKey(id))
                                        viewModel.answerXOffset[id] = 0f
                                    if (!viewModel.answerYOffset.containsKey(id))
                                        viewModel.answerYOffset[id] = 0f
                                    DraggableAnswerCard(
                                        item = item.data,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .size(viewModel.cardSize[id]!!)
                                            .offset {
                                                IntOffset(
                                                    viewModel.answerXOffset[id]!!.roundToInt(),
                                                    viewModel.answerYOffset[id]!!.roundToInt()
                                                )
                                            }
                                            .onGloballyPositioned { coordinates ->
                                                viewModel.boxRectAnswer[id] = coordinates.boundsInWindow()
                                            }
                                            .pointerInput(Unit) {
                                                detectDragGestures(
                                                    onDrag = { change, dragAmount ->
                                                        change.consume()
                                                        viewModel.answerXOffset[id] =
                                                            viewModel.answerXOffset[id]!! + dragAmount.x
                                                        viewModel.answerYOffset[id] =
                                                            viewModel.answerYOffset[id]!! + dragAmount.y
                                                        viewModel.cardSize[id] = minSize
                                                    },
                                                    onDragEnd = {
                                                        var checkNull = false
                                                        for ((ind, entry) in viewModel.boxRectQuiz.entries.withIndex()) {
                                                            val (_, rect) = entry
                                                            if (viewModel.dataQuiz[ind].hasContent)
                                                                continue

                                                            if (viewModel.boxRectAnswer[id]!!.overlaps(
                                                                    rect
                                                                )
                                                            ) {
                                                                viewModel.cardSize[id] = minSize
                                                                viewModel.dataQuiz = viewModel.dataQuiz
                                                                    .apply {
                                                                        this[ind] =
                                                                            this[ind].copy(
                                                                                data = item.data,
                                                                                showCard = true,
                                                                                emp = id,
                                                                                hasContent = true
                                                                            )
                                                                    }
                                                                checkNull = true
                                                                viewModel.cardSize[id] = 0.dp
                                                                viewModel.answerXOffset[id] = 0f
                                                                viewModel.answerYOffset[id] = 0f
                                                                break
                                                            }
                                                        }
                                                        if (!checkNull) {
                                                            viewModel.cardSize[id] = maxSize
                                                            viewModel.answerXOffset[id] = 0f
                                                            viewModel.answerYOffset[id] = 0f
                                                        }
                                                    }
                                                )
                                            }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
                        viewModel.showLoading = false
                    }
                }

                ButtonNext(
                    onclick = {
                        navController.navigate("write")
                    },
                    text = stringResource(id = R.string.next),
                    painter = painterResource(id = R.drawable.ic_next),
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 50.dp)
                        .fillMaxWidth()
                        .constrainAs(buttonRef) {
                            bottom.linkTo(parent.bottom)
                        }
                )
            }

        }
    }
}


@Preview
@Composable
fun SpellScreenPreview() {
    val navController = rememberNavController()
    SpellScreen(navController = navController)
}
