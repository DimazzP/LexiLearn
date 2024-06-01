package com.example.lexilearn.ui.views.pQuiz.pSpell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.min
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
import coil.compose.rememberImagePainter
import com.example.lexilearn.R
import com.example.lexilearn.domain.models.ModelSpell
import com.example.lexilearn.ui.theme.ctextGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpellScreen(navController: NavController) {

    var rectColumnAnswer by remember { mutableStateOf(Rect.Zero) }

    val cardSize = remember {
        mutableStateMapOf<Int, Dp>()
    }

    val maxSize = 120.dp

    val minSize = 70.dp

    var dataQuiz = remember {
        mutableStateListOf(
            ModelSpell(1, false, "r ", showCard = false),
            ModelSpell(2, false, "i", showCard = false),
            ModelSpell(3, true, "?", showCard = false),
            ModelSpell(4, false, "e", showCard = false),
        )
    }

    val listAnswer =
        remember {
            mutableStateListOf(
                ModelAnswerRead(1, "a"),
                ModelAnswerRead(2, "c"),
                ModelAnswerRead(3, "d"),
                ModelAnswerRead(4, "k")
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
            headerText = stringResource(id = R.string.spelltitle),
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
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Susun Kata", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                                CardQuiz(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .size(minSize)
                                        .onGloballyPositioned { coordinates ->
                                            if(dt.type)
                                                boxRectQuiz[id] = coordinates.boundsInWindow()
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
                                                                        cardSize[emDt] = maxSize
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
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ctextGray)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .onGloballyPositioned {
                                    rectColumnAnswer = it.boundsInWindow()
                                },
                        ) {
                            listAnswer.chunked(2).forEach { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    rowItems.forEach { item ->
                                        val id = item.id
                                        if (!cardSize.containsKey(id))
                                            cardSize[id] = maxSize
                                        if (!boxRectAnswer.containsKey(id))
                                            boxRectAnswer[id] = Rect.Zero
                                        if (!answerXOffset.containsKey(id))
                                            answerXOffset[id] = 0f
                                        if (!answerYOffset.containsKey(id))
                                            answerYOffset[id] = 0f
                                        DraggableAnswerCard(
                                            item = item.data,
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .size(cardSize[id]!!)
                                                .offset {
                                                    IntOffset(
                                                        answerXOffset[id]!!.roundToInt(),
                                                        answerYOffset[id]!!.roundToInt()
                                                    )
                                                }
                                                .onGloballyPositioned { coordinates ->
                                                    boxRectAnswer[id] = coordinates.boundsInWindow()
                                                }
                                                .pointerInput(Unit) {
                                                    detectDragGestures(
                                                        onDrag = { change, dragAmount ->
                                                            change.consume()
                                                            answerXOffset[id] =
                                                                answerXOffset[id]!! + dragAmount.x
                                                            answerYOffset[id] =
                                                                answerYOffset[id]!! + dragAmount.y
                                                            cardSize[id] = minSize
                                                        },
                                                        onDragEnd = {
                                                            var checkNull = false
                                                            for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
                                                                val (_, rect) = entry
                                                                if (dataQuiz[ind].hasContent)
                                                                    continue

                                                                if (boxRectAnswer[id]!!.overlaps(
                                                                        rect
                                                                    )
                                                                ) {
                                                                    cardSize[id] = minSize
                                                                    dataQuiz = dataQuiz
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
                                                                    cardSize[id] = 0.dp
                                                                    answerXOffset[id] = 0f
                                                                    answerYOffset[id] = 0f
                                                                    break
                                                                }
                                                            }
                                                            if (!checkNull) {
                                                                cardSize[id] = maxSize
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
//                        LazyVerticalGrid(
//                            columns = GridCells.Fixed(2),
//                            contentPadding = PaddingValues(12.dp),
//                            modifier = Modifier.fillMaxSize()
//                        ) {
//                            items(listAnswer) { item ->
//                                val id = item.id
//                                if (!cardRatio.containsKey(id))
//                                    cardRatio[id] = 1f
//                                if (!boxRectAnswer.containsKey(id))
//                                    boxRectAnswer[id] = Rect.Zero
//                                if (!answerXOffset.containsKey(id))
//                                    answerXOffset[id] = 0f
//                                if (!answerYOffset.containsKey(id))
//                                    answerYOffset[id] = 0f
//                                DraggableAnswerCard(
//                                    item = item.data,
//                                    modifier = Modifier
//                                        .padding(10.dp)
//                                        .aspectRatio(cardRatio[id]!!)
//                                        .offset {
//                                            IntOffset(
//                                                answerXOffset[id]!!.roundToInt(),
//                                                answerYOffset[id]!!.roundToInt()
//                                            )
//                                        }
//                                        .onGloballyPositioned { coordinates ->
//                                            boxRectAnswer[id] = coordinates.boundsInWindow()
//                                        }.pointerInput(Unit) {
//                                        detectDragGestures(
//                                            onDrag = { change, dragAmount ->
//                                                change.consume()
//                                                answerXOffset[id] =
//                                                    answerXOffset[id]!! + dragAmount.x
//                                                answerYOffset[id] =
//                                                    answerYOffset[id]!! + dragAmount.y
////                                                cardWidth[id] = minWidtR
////                                                cardHeight[id] = minHeightR
//                                            },
//                                            onDragEnd = {
////                                                var checkNull = false
////                                                for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
////                                                    val (_, rect) = entry
////                                                    if (dataQuiz[ind].hasContent)
////                                                        continue
////
////                                                    if (boxRectAnswer[id]!!.overlaps(rect)) {
////                                                        cardWidth[id] = minWidtR
////                                                        cardHeight[id] = minHeightR
////                                                        dataQuiz = dataQuiz
////                                                            .apply {
////                                                                this[ind] = this[ind].copy(
////                                                                    data = item.data,
////                                                                    showCard = true,
////                                                                    emp = id,
////                                                                    hasContent = true
////                                                                )
////                                                            }
////                                                        checkNull = true
////                                                        cardWidth[id] = 0.dp
////                                                        cardHeight[id] = 0.dp
////                                                        answerXOffset[id] = 0f
////                                                        answerYOffset[id] = 0f
////                                                        break
////                                                    }
////                                                }
////                                                if (!checkNull) {
////                                                    cardWidth[id] = maxWidthR
////                                                    cardHeight[id] = maxHeightR
////                                                    answerXOffset[id] = 0f
////                                                    answerYOffset[id] = 0f
////                                                }
//                                            }
//                                        )
//                                    }
//                                )
//                            }
//                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
//                    FlowRow(
//                        modifier = Modifier.padding(12.dp),
//                    ) {
//                        dataQuiz.forEach { dt ->
//                            val id = dt.id
//                            if (!boxRectDragable.containsKey(id))
//                                boxRectDragable[id] = Rect.Zero
//                            if (!boxRectQuiz.containsKey(id))
//                                boxRectQuiz[id] = Rect.Zero
//                            if (!quizXOffset.containsKey(id))
//                                quizXOffset[id] = 0f
//                            if (!quizYOffset.containsKey(id))
//                                quizYOffset[id] = 0f
//                            if (dt.type) {
//                                CardQuiz(
//                                    modifier = Modifier
//                                        .padding(vertical = 10.dp)
//                                        .width(minWidtR)
//                                        .height(minHeightR)
//                                        .onGloballyPositioned { coordinates ->
//                                            boxRectQuiz[id] = coordinates.boundsInWindow()
//                                        }
//                                ) {
//                                    Text(
//                                        text = dt.data, // Use the state to display text
//                                        color = ctextWhite,
//                                        fontWeight = FontWeight.Bold,
//                                        textAlign = TextAlign.Center,
//                                        modifier = Modifier.fillMaxWidth()
//                                    )
//                                    if (dt.showCard) {
//                                        DraggableAnswerCard(
//                                            item = dt.data,
//                                            modifier = Modifier
//                                                .offset {
//                                                    val xOffset = quizXOffset[id] ?: 0f
//                                                    val yOffset = quizYOffset[id] ?: 0f
//                                                    IntOffset(
//                                                        xOffset.roundToInt(),
//                                                        yOffset.roundToInt()
//                                                    )
//                                                }
//                                                .onGloballyPositioned {
//                                                    boxRectDragable[id] = it.boundsInWindow()
//                                                }
//                                                .fillMaxSize()
//                                                .pointerInput(Unit) {
//                                                    detectDragGestures(
//                                                        onDrag = { change, dragAmount ->
//                                                            change.consume()
//                                                            quizXOffset[id] =
//                                                                quizXOffset[id]!! + dragAmount.x
//                                                            quizYOffset[id] =
//                                                                quizYOffset[id]!! + dragAmount.y
//                                                        },
//                                                        onDragEnd = {
//                                                            var checkNull = false
//                                                            for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
//                                                                val (key, rect) = entry
//                                                                if (key == id)
//                                                                    continue
//
//                                                                if (dataQuiz[ind].hasContent)
//                                                                    continue
//
//                                                                if (boxRectDragable[id]!!.overlaps(
//                                                                        rect
//                                                                    )
//                                                                ) {
//                                                                    dataQuiz = dataQuiz.apply {
//                                                                        this[ind] = this[ind].copy(
//                                                                            data = dt.data,
//                                                                            showCard = true,
//                                                                            emp = dt.emp,
//                                                                            hasContent = true
//                                                                        )
//                                                                    }
//                                                                    dt.apply {
//                                                                        hasContent = false
//                                                                        showCard = false
//                                                                        data = "?"
//                                                                    }
//                                                                    checkNull = true
//                                                                    quizXOffset[id] = 0f
//                                                                    quizYOffset[id] = 0f
//                                                                    break
//                                                                }
//
//                                                            }
//                                                            if (boxRectDragable[id]!!.overlaps(
//                                                                    rectColumnAnswer
//                                                                )
//                                                            ) {
//                                                                val emDt = dt.emp
//                                                                if (emDt != null) {
//                                                                    dt.apply {
//                                                                        hasContent = false
//                                                                        showCard = false
//                                                                        data = "?"
//                                                                    }
//                                                                    checkNull = true
//                                                                    cardWidth[emDt] = maxWidthR
//                                                                    cardHeight[emDt] = maxHeightR
//                                                                    quizXOffset[id] = 0f
//                                                                    quizYOffset[id] = 0f
//                                                                    dt.data = "?"
//                                                                }
//                                                            }
//                                                            if (!checkNull) {
//                                                                quizXOffset[id] = 0f
//                                                                quizYOffset[id] = 0f
//                                                            }
//                                                        }
//                                                    )
//                                                }
//                                        )
//                                    }
//                                }
//                            } else {
//                                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
//                                    Text(
//                                        text = dt.data,
//                                        color = ctextBlack,
//                                        fontSize = 20.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        textAlign = TextAlign.Center,
//                                    )
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(30.dp))
//                HorizontalLine()
//                Spacer(modifier = Modifier.height(40.dp))
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .onGloballyPositioned {
//                            rectColumnAnswer = it.boundsInWindow()
//                        },
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    for (i in 0 until listAnswer.size) {
//                        val item = listAnswer[i]
//                        val id = item.id
//                        if (!boxRectAnswer.containsKey(id))
//                            boxRectAnswer[id] = Rect.Zero
//                        if (!answerXOffset.containsKey(id))
//                            answerXOffset[id] = 0f
//                        if (!answerYOffset.containsKey(id))
//                            answerYOffset[id] = 0f
//                        if (!cardWidth.containsKey(id))
//                            cardWidth[id] = maxWidthR
//                        if (!cardHeight.containsKey(id))
//                            cardHeight[id] = maxHeightR
//                        if (item.showCard) {
//                            DraggableAnswerCard(
//                                item = item.data,
//                                modifier = Modifier
//                                    .padding(vertical = 4.dp)
//                                    .offset {
//                                        IntOffset(
//                                            answerXOffset[id]!!.roundToInt(),
//                                            answerYOffset[id]!!.roundToInt()
//                                        )
//                                    }
//                                    .onGloballyPositioned {
//                                        boxRectAnswer[id] = it.boundsInWindow()
//                                    }
//                                    .width(cardWidth[id]!!)
//                                    .height(cardHeight[id]!!)
//                                    .pointerInput(Unit) {
//                                        detectDragGestures(
//                                            onDrag = { change, dragAmount ->
//                                                change.consume()
//                                                answerXOffset[id] =
//                                                    answerXOffset[id]!! + dragAmount.x
//                                                answerYOffset[id] =
//                                                    answerYOffset[id]!! + dragAmount.y
//                                                cardWidth[id] = minWidtR
//                                                cardHeight[id] = minHeightR
//                                            },
//                                            onDragEnd = {
//                                                var checkNull = false
//                                                for ((ind, entry) in boxRectQuiz.entries.withIndex()) {
//                                                    val (_, rect) = entry
//                                                    if (dataQuiz[ind].hasContent)
//                                                        continue
//
//                                                    if (boxRectAnswer[id]!!.overlaps(rect)) {
//                                                        cardWidth[id] = minWidtR
//                                                        cardHeight[id] = minHeightR
//                                                        dataQuiz = dataQuiz
//                                                            .apply {
//                                                                this[ind] = this[ind].copy(
//                                                                    data = item.data,
//                                                                    showCard = true,
//                                                                    emp = id,
//                                                                    hasContent = true
//                                                                )
//                                                            }
//                                                        checkNull = true
//                                                        cardWidth[id] = 0.dp
//                                                        cardHeight[id] = 0.dp
//                                                        answerXOffset[id] = 0f
//                                                        answerYOffset[id] = 0f
//                                                        break
//                                                    }
//                                                }
//                                                if (!checkNull) {
//                                                    cardWidth[id] = maxWidthR
//                                                    cardHeight[id] = maxHeightR
//                                                    answerXOffset[id] = 0f
//                                                    answerYOffset[id] = 0f
//                                                }
//                                            }
//                                        )
//                                    }
//                            )
//                        }
//                    }
//                }
            }
        }
    }
}

//@Composable
//fun CustomGridScreen(data: List<ModelAnswerRead>, columns: Int = 2) {
//    Column {
//        data.chunked(columns).forEach { rowItems ->
//            Row(modifier = Modifier.fillMaxWidth()) {
//                rowItems.forEach { item ->
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .aspectRatio(1f)
//                            .padding(8.dp)
//                            .background(Color.Blue),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(text = item.data.toString(), color = Color.White, fontSize = 24.sp)
//                    }
//                }
//            }
//        }
//    }
//}