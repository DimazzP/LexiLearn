package com.example.lexilearn.ui.views.pQuiz.pRead

import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.DraggableAnswerCard
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReadScreen(viewModel: ReadViewModel = viewModel()) {
    val dataQuiz = viewModel.dataQuiz.observeAsState()
    val listAnswer = viewModel.dataAnswer.observeAsState()

    val maxWidthR = 280.dp
    val maxHeightR = 60.dp

    val minWidthR = 90.dp
    val minHeightR = 40.dp


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (buttonRef) = createRefs()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    "",
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
                FlowRow(
                    modifier = Modifier.padding(12.dp),
                ) {
                    dataQuiz.value?.forEach { dt ->
                        val id = dt.id
                        if (!viewModel.boxRectDragable.containsKey(id))
                            viewModel.boxRectDragable[id] = Rect.Zero
                        if (!viewModel.boxRectQuiz.containsKey(id))
                            viewModel.boxRectQuiz[id] = Rect.Zero
                        if (!viewModel.quizXOffset.containsKey(id))
                            viewModel.quizXOffset[id] = 0f
                        if (!viewModel.quizYOffset.containsKey(id))
                            viewModel.quizYOffset[id] = 0f
                        if (dt.type) {
                            CardQuiz(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .width(minWidthR)
                                    .height(minHeightR)
                                    .onGloballyPositioned { coordinates ->
                                        viewModel.boxRectQuiz[id] = coordinates.boundsInWindow()
                                    }
                            ) {
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
                                                viewModel.boxRectDragable[id] = it.boundsInWindow()
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

                                                            if (viewModel.dataQuiz.value!![ind].hasContent)
                                                                continue

                                                            if (viewModel.boxRectDragable[id]!!.overlaps(
                                                                    rect
                                                                )
                                                            ) {
                                                                dataQuiz.value?.mapIndexed { index, item ->
                                                                    if (index == ind) {
                                                                        item.copy(
                                                                            data = dt.data,
                                                                            showCard = true,
                                                                            emp = id,
                                                                            hasContent = true
                                                                        )
                                                                    } else {
                                                                        item
                                                                    }
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
                                                                viewModel.cardWidth[emDt] =
                                                                    maxWidthR
                                                                viewModel.cardHeight[emDt] =
                                                                    maxHeightR
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
                            }
                        } else {
                            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                Text(
                                    text = dt.data,
                                    color = ctextBlack,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                )
                            }

                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            HorizontalLine()
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned {
                        viewModel.rectColumnAnswer.value = it.boundsInWindow()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                listAnswer.value?.mapIndexed() { id, item ->
                    if (!viewModel.boxRectAnswer.containsKey(id))
                        viewModel.boxRectAnswer[id] = Rect.Zero
                    if (!viewModel.answerXOffset.containsKey(id))
                        viewModel.answerXOffset[id] = 0f
                    if (!viewModel.answerYOffset.containsKey(id))
                        viewModel.answerYOffset[id] = 0f
                    if (!viewModel.cardWidth.containsKey(id))
                        viewModel.cardWidth[id] = maxWidthR
                    if (!viewModel.cardHeight.containsKey(id))
                        viewModel.cardHeight[id] = maxHeightR
                    if (item.showCard) {
                        DraggableAnswerCard(
                            item = item.data,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .offset {
                                    IntOffset(
                                        viewModel.answerXOffset[id]!!.roundToInt(),
                                        viewModel.answerYOffset[id]!!.roundToInt()
                                    )
                                }
                                .onGloballyPositioned {
                                    viewModel.boxRectAnswer[id] = it.boundsInWindow()
                                }
                                .width(viewModel.cardWidth[id]!!)
                                .height(viewModel.cardHeight[id]!!)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            viewModel.answerXOffset[id] =
                                                viewModel.answerXOffset[id]!! + dragAmount.x
                                            viewModel.answerYOffset[id] =
                                                viewModel.answerYOffset[id]!! + dragAmount.y
                                            viewModel.cardWidth[id] = minWidthR
                                            viewModel.cardHeight[id] = minHeightR
                                        },
                                        onDragEnd = {
                                            var checkNull = false
                                            for ((ind, entry) in viewModel.boxRectQuiz.entries.withIndex()) {
                                                val (_, rect) = entry
                                                if (dataQuiz.value!![ind].hasContent) continue
                                                if (viewModel.boxRectAnswer[id]!!.overlaps(rect)) {
                                                    viewModel.cardWidth[id] = minWidthR
                                                    viewModel.cardHeight[id] = minHeightR
                                                    dataQuiz.value?.mapIndexed { index, dt ->
                                                        if (index == ind) {
                                                            println(dt)
                                                            dt.data = item.data
                                                        }
                                                    }
                                                    checkNull = true
                                                    viewModel.cardWidth[id] = 0.dp
                                                    viewModel.cardHeight[id] = 0.dp
                                                    viewModel.answerXOffset[id] = 0f
                                                    viewModel.answerYOffset[id] = 0f
                                                    break
                                                }
                                            }
                                            if (!checkNull) {
                                                viewModel.cardWidth[id] = maxWidthR
                                                viewModel.cardHeight[id] = maxHeightR
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

        ButtonNext(
            onclick = { },
            text = stringResource(id = R.string.next),
            painter = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 50.dp)
                .fillMaxWidth()
                .constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom)
                }
        )

        LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
            viewModel.showLoading = false
        }
    }
}

@Preview
@Composable
fun ReadScreenPreview() {
    val viewModel = remember { ReadViewModel() } // Create a mock ViewModel instance
    ReadScreen(viewModel = viewModel)
}
