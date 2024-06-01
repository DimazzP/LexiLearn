package com.example.lexilearn.ui.views.pQuiz.pRead

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.lexilearn.R
import com.example.lexilearn.domain.models.ModelAnswerRead
import com.example.lexilearn.domain.models.ModelWords
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.DraggableAnswerCard
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReadScreen(navController: NavController) {

    var rectColumnAnswer by remember { mutableStateOf(Rect.Zero) }

    val cardWidth = remember {
        mutableStateMapOf<Int, Dp>()
    }

    val cardHeight = remember {
        mutableStateMapOf<Int, Dp>()
    }

    val maxWidthR = 280.dp
    val maxHeightR = 60.dp

    val minWidtR = 90.dp
    val minHeightR = 40.dp

    var dataQuiz = remember {
        mutableStateListOf(
            ModelWords(1, false, "The dog ", showCard = false),
            ModelWords(2, true, "?", showCard = false),
            ModelWords(3, false, " and The Cat ", showCard = false),
            ModelWords(4, true, "?", showCard = false),
        )
    }

    val listAnswer =
        remember {
            mutableStateListOf(
                ModelAnswerRead(1, "chases"),
                ModelAnswerRead(2, "run"),
                ModelAnswerRead(3, "watches")
            )
        }

    val quizXOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    val quizYOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    val boxRectDragable = remember {
        mutableStateMapOf<Int, Rect>()
    }

    val boxRectQuiz = remember {
        mutableStateMapOf<Int, Rect>()
    }

    val answerXOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    val answerYOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    val boxRectAnswer = remember {
        mutableStateMapOf<Int, Rect>()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        GradientQuiz(
            navController = navController,
            headerText = "Learn to Read",
            modifier = Modifier.fillMaxSize()
        ) {
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
                        FlowRow(
                            modifier = Modifier.padding(12.dp),
                        ) {
                            dataQuiz.forEach { dt ->
                                val id = dt.id
                                if (!boxRectDragable.containsKey(id))
                                    boxRectDragable[id] = Rect.Zero
                                if (!boxRectQuiz.containsKey(id))
                                    boxRectQuiz[id] = Rect.Zero
                                if (!quizXOffset.containsKey(id))
                                    quizXOffset[id] = 0f
                                if (!quizYOffset.containsKey(id))
                                    quizYOffset[id] = 0f
                                if (dt.type) {
                                    CardQuiz(
                                        modifier = Modifier
                                            .padding(vertical = 10.dp)
                                            .width(minWidtR)
                                            .height(minHeightR)
                                            .onGloballyPositioned { coordinates ->
                                                boxRectQuiz[id] = coordinates.boundsInWindow()
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
                                                        val xOffset = quizXOffset[id] ?: 0f
                                                        val yOffset = quizYOffset[id] ?: 0f
                                                        IntOffset(
                                                            xOffset.roundToInt(),
                                                            yOffset.roundToInt()
                                                        )
                                                    }
                                                    .onGloballyPositioned {
                                                        boxRectDragable[id] = it.boundsInWindow()
                                                    }
                                                    .fillMaxSize()
                                                    .pointerInput(Unit) {
                                                        detectDragGestures(
                                                            onDrag = { change, dragAmount ->
                                                                change.consume()
                                                                quizXOffset[id] =
                                                                    quizXOffset[id]!! + dragAmount.x
                                                                quizYOffset[id] =
                                                                    quizYOffset[id]!! + dragAmount.y
                                                            },
                                                            onDragEnd = {
                                                                var checkNull = false
                                                                for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
                                                                    val (key, rect) = entry
                                                                    if (key == id)
                                                                        continue

                                                                    if (dataQuiz[ind].hasContent)
                                                                        continue

                                                                    if (boxRectDragable[id]!!.overlaps(
                                                                            rect
                                                                        )
                                                                    ) {
                                                                        dataQuiz = dataQuiz.apply {
                                                                            this[ind] = this[ind].copy(
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
                                                                        quizXOffset[id] = 0f
                                                                        quizYOffset[id] = 0f
                                                                        break
                                                                    }

                                                                }
                                                                if (boxRectDragable[id]!!.overlaps(
                                                                        rectColumnAnswer
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
                                                                        cardWidth[emDt] = maxWidthR
                                                                        cardHeight[emDt] = maxHeightR
                                                                        quizXOffset[id] = 0f
                                                                        quizYOffset[id] = 0f
                                                                        dt.data = "?"
                                                                    }
                                                                }
                                                                if (!checkNull) {
                                                                    quizXOffset[id] = 0f
                                                                    quizYOffset[id] = 0f
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
                                rectColumnAnswer = it.boundsInWindow()
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        for (i in 0 until listAnswer.size) {
                            val item = listAnswer[i]
                            val id = item.id
                            if (!boxRectAnswer.containsKey(id))
                                boxRectAnswer[id] = Rect.Zero
                            if (!answerXOffset.containsKey(id))
                                answerXOffset[id] = 0f
                            if (!answerYOffset.containsKey(id))
                                answerYOffset[id] = 0f
                            if (!cardWidth.containsKey(id))
                                cardWidth[id] = maxWidthR
                            if (!cardHeight.containsKey(id))
                                cardHeight[id] = maxHeightR
                            if (item.showCard) {
                                DraggableAnswerCard(
                                    item = item.data,
                                    modifier = Modifier
                                        .padding(vertical = 4.dp)
                                        .offset {
                                            IntOffset(
                                                answerXOffset[id]!!.roundToInt(),
                                                answerYOffset[id]!!.roundToInt()
                                            )
                                        }
                                        .onGloballyPositioned {
                                            boxRectAnswer[id] = it.boundsInWindow()
                                        }
                                        .width(cardWidth[id]!!)
                                        .height(cardHeight[id]!!)
                                        .pointerInput(Unit) {
                                            detectDragGestures(
                                                onDrag = { change, dragAmount ->
                                                    change.consume()
                                                    answerXOffset[id] =
                                                        answerXOffset[id]!! + dragAmount.x
                                                    answerYOffset[id] =
                                                        answerYOffset[id]!! + dragAmount.y
                                                    cardWidth[id] = minWidtR
                                                    cardHeight[id] = minHeightR
                                                },
                                                onDragEnd = {
                                                    var checkNull = false
                                                    for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
                                                        val (_, rect) = entry
                                                        if (dataQuiz[ind].hasContent)
                                                            continue

                                                        if (boxRectAnswer[id]!!.overlaps(rect)) {
                                                            cardWidth[id] = minWidtR
                                                            cardHeight[id] = minHeightR
                                                            dataQuiz = dataQuiz
                                                                .apply {
                                                                    this[ind] = this[ind].copy(
                                                                        data = item.data,
                                                                        showCard = true,
                                                                        emp = id,
                                                                        hasContent = true
                                                                    )
                                                                }
                                                            checkNull = true
                                                            cardWidth[id] = 0.dp
                                                            cardHeight[id] = 0.dp
                                                            answerXOffset[id] = 0f
                                                            answerYOffset[id] = 0f
                                                            break
                                                        }
                                                    }
                                                    if (!checkNull) {
                                                        cardWidth[id] = maxWidthR
                                                        cardHeight[id] = maxHeightR
                                                        answerXOffset[id] = 0f
                                                        answerYOffset[id] = 0f
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
                    onclick = { navController.navigate("spell")
                    },
                    text = stringResource(id = R.string.next),
                    painter = painterResource(id = R.drawable.ic_next),
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 50.dp)
                        .fillMaxWidth().constrainAs(buttonRef){
                            bottom.linkTo(parent.bottom)
                        }
                )
            }

        }
    }
}