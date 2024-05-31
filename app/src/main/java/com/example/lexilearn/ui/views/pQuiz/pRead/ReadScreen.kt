package com.example.lexilearn.ui.views.pQuiz.pRead

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lexilearn.domain.models.ModelAnswerRead
import com.example.lexilearn.domain.models.ModelWords
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
    var listAnswer =
        remember {
            mutableStateListOf(
                ModelAnswerRead(1, "chases"),
                ModelAnswerRead(2, "run"),
                ModelAnswerRead(3, "watches")
            )
        }
    var rectColumnAnswer by remember { mutableStateOf(Rect.Zero) }

    var cardWidth = remember {
        mutableStateMapOf<Int, Dp>()
    }

    var cardHeight = remember {
        mutableStateMapOf<Int, Dp>()
    }

    val minWidtR = 90.dp
    val minHeightR = 40.dp

    var dataQuiz by remember {
        mutableStateOf(
            mutableListOf(
                ModelWords(1, false, "The dog ", showCard = false),
                ModelWords(2, true, "?", showCard = false),
                ModelWords(3, false, " and The Cat ", showCard = false),
                ModelWords(4, true, "?", showCard = false),
            )
        )
    }

    var quizXOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    var quizYOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    var boxRectDragable = remember {
        mutableStateMapOf<Int, Rect>()
    }

    var boxRectQuiz = remember {
        mutableStateMapOf<Int, Rect>()
    }

    var answerXOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    var answerYOffset = remember {
        mutableStateMapOf<Int, Float>()
    }

    var boxRectAnswer = remember {
        mutableStateMapOf<Int, Rect>()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        GradientQuiz(
            navController = navController,
            headerText = "Learn to Read",
            modifier = Modifier.fillMaxSize()
        ) {
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
                        .padding()
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
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
                                                                if(key==id)
                                                                    continue

                                                                if(dataQuiz[ind].hasContent)
                                                                    continue

                                                                if (boxRectDragable[id]!!.overlaps(rect)) {
                                                                    dataQuiz = dataQuiz.toMutableList().apply {
                                                                        this[ind] = this[ind].copy(
                                                                            data = dt.data,
                                                                            showCard = true,
                                                                            emp = dt.emp
                                                                        )
                                                                    }
                                                                    dt.showCard = false
                                                                    checkNull = true
                                                                    quizXOffset[id] = 0f
                                                                    quizYOffset[id] = 0f
                                                                    break
                                                                }

                                                            }
                                                            if(boxRectDragable[id]!!.overlaps(rectColumnAnswer)){
                                                                val emDt = dt.emp
                                                                if(emDt!=null){
                                                                    dt.showCard = false
                                                                    checkNull = true
                                                                    cardWidth[emDt] = 280.dp
                                                                    cardHeight[emDt] = 60.dp
                                                                    quizXOffset[id] = 0f
                                                                    quizYOffset[id] = 0f
                                                                    dt.data = "?"
                                                                }
                                                            }
                                                            if(!checkNull){
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
                    for(i in 0 until  listAnswer.size) {
                            val item = listAnswer[i]
                            val id = item.id
                            if (!boxRectAnswer.containsKey(id))
                                boxRectAnswer[id] = Rect.Zero
                            if (!answerXOffset.containsKey(id))
                                answerXOffset[id] = 0f
                            if (!answerYOffset.containsKey(id))
                                answerYOffset[id] = 0f
                            if (!cardWidth.containsKey(id))
                                cardWidth[id] = 280.dp
                            if (!cardHeight.containsKey(id))
                                cardHeight[id] = 60.dp
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
//                                                Log.d("fatalkutest1", idx.toString())
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
                                                        if(dataQuiz[ind].hasContent)
                                                            continue

                                                        if (boxRectAnswer[id]!!.overlaps(rect)) {
                                                            cardWidth[id] = minWidtR
                                                            cardHeight[id] = minHeightR
                                                            dataQuiz = dataQuiz
                                                                .toMutableList()
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
                                                        cardWidth[id] = 280.dp
                                                        cardHeight[id] = 60.dp
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
        }
    }
}


