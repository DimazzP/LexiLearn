package com.example.lexilearn.ui.views.pQuiz.pRead

import android.content.ClipDescription
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lexilearn.domain.models.ModelWords
import com.example.lexilearn.ui.components.AutoSizeText
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.DraggableAnswerCard
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.csecondary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReadScreen(navController: NavController) {
    var offsetX1 by remember { mutableStateOf(0f) }
    var offsetY1 by remember { mutableStateOf(0f) }
    var box1Rect by remember { mutableStateOf(Rect.Zero) }
    var box2Rect by remember { mutableStateOf(Rect.Zero) }
    var cardQuizText by remember { mutableStateOf("?") } // State to store the text for CardQuiz
    val listAnswer = remember { mutableStateListOf("chases") }
    var cardWidth by remember { mutableStateOf(280.dp) } // Default width
    var cardHeight by remember { mutableStateOf(60.dp) } // Default height

    val minWidtR = 90.dp
    val minHeightR = 40.dp
//    , "watch", "run"


    var dataQuiz = listOf<ModelWords>(
        ModelWords(false, "The dog", false),
        ModelWords(true, "chases", false),
    )

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
                        dataQuiz.forEach {
                            if(it.type){
                                CardQuiz(
                                    modifier = Modifier
                                        .width(minWidtR)
                                        .height(minHeightR)
                                        .onGloballyPositioned { coordinates ->
                                            box2Rect = coordinates.boundsInWindow()
                                        }
                                ) {
                                    Text(
                                        text = cardQuizText, // Use the state to display text
                                        color = ctextWhite,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }else{
                                Box(modifier = Modifier.align(Alignment.CenterVertically)){
                                    Text(
                                        text = it.data,
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
                        .background(ctextBlack)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    listAnswer.forEachIndexed { index, item ->
                        DraggableAnswerCard(
                            item = item,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
                                .onGloballyPositioned {
                                    box1Rect = it.boundsInWindow()
                                }
                                .width(cardWidth)
                                .height(cardHeight)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            offsetX1 += dragAmount.x
                                            offsetY1 += dragAmount.y
                                            cardWidth = minWidtR
                                            cardHeight = minHeightR
                                        },
                                        onDragEnd = {
                                            if (box1Rect.overlaps(box2Rect)) {
                                                cardQuizText = item
                                                cardWidth = minWidtR
                                                cardHeight = minHeightR
                                                val difX = box1Rect.topLeft.x - offsetX1
                                                val difY = box1Rect.topLeft.y - offsetY1
                                                offsetX1 = box2Rect.topLeft.x - difX
                                                offsetY1 = box2Rect.topLeft.y - difY

                                            } else {
                                                cardWidth = 280.dp
                                                cardHeight = 60.dp
                                                offsetX1 = 0f
                                                offsetY1 = 0f
                                            }
                                        }
                                    )
                                }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}


//@Composable
//fun ReadScreen(navController: NavController) {
//    val context = LocalContext.current
//    val density = LocalDensity.current
//    var offsetX1 by remember { mutableStateOf(0f) }
//    var offsetY1 by remember { mutableStateOf(0f) }
//    var offsetX2 by remember { mutableStateOf(0f) }
//    var offsetY2 by remember { mutableStateOf(0f) }
//    var box1Position by remember { mutableStateOf(Offset.Zero) }
////    var box2Position by remember { mutableStateOf(Offset.Zero) }
//    var draggingItem by remember { mutableStateOf<Int?>(null) }
//    var box1Rect by remember { mutableStateOf(Rect.Zero) }
//    var box2Rect by remember { mutableStateOf(Rect.Zero) }
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
//            // Box 1 (Container)
//            Box(
//                modifier = Modifier
//                    .size(300.dp)
//                    .zIndex(2f)
//                    .background(Color.Red)
//                    .onGloballyPositioned { coordinates ->
//                        box1Position = coordinates.positionInParent()
//                    }
//            ) {
//                // Box Item (Draggable Box inside Box 1)
//                Box(
//                    modifier = Modifier
//                        .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
//                        .size(100.dp)
//                        .zIndex(2f)
//                        .background(Color.Blue)
//                        .onGloballyPositioned {coordinates ->
////                            box1Position = coordinates.positionInWindow()
//                            box1Rect = coordinates.boundsInWindow()
//                        }
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragStart = {
//                                    draggingItem = 1
//                                },
//                                onDrag = { change, dragAmount ->
//                                    change.consume()
//                                    offsetX1 += dragAmount.x
//                                    offsetY1 += dragAmount.y
//                                },
//                                onDragEnd = {
//                                    Log.d("testkuco1", "x1 = ${box1Rect.topLeft.x}, y1 = ${box1Rect.topLeft.y}")
//                                    Log.d("testkuco1", "x1 = ${offsetX1}, y1 = ${offsetY1}")
//                                    val tesx = box1Rect.topLeft.x - offsetX1
//                                    val resx = offsetX1 + tesx
//                                    val tesy = box1Rect.topLeft.y - offsetY1
//                                    val resy = offsetY1 + tesy
//                                    Log.e("testkuco1", "x1 = ${resx}, y1 = ${resy}")
//                                    Log.d("testkuco1", "-----------")
//                                    val box1Center = Offset(
//                                        x = box1Rect.left + box1Rect.width / 2,
//                                        y = box1Rect.top + box1Rect.height / 2
//                                    )
//                                    // Check if the center of box1Rect overlaps with box2Rect
//                                    val box2Left = box2Rect.left
//                                    val box2Top = box2Rect.top
//                                    val box2Right = box2Rect.right
//                                    val box2Bottom = box2Rect.bottom
//                                        if (box1Center.x in box2Left..box2Right && box1Center.y in box2Top..box2Bottom) {
//                                            offsetX1 = box2Rect.topLeft.x - tesx
//                                            offsetY1 = box2Rect.topLeft.y - tesy
//                                            Log.e("testkuco1m", "x1 = ${offsetX1}, y1 = ${offsetY1}")
//                                        Toast.makeText(context, "Box1 is now aligned with Box2", Toast.LENGTH_SHORT).show()
//                                    }
//
//                                    draggingItem = null
//                                }
//                            )
//                        }
//                ) {
//                    Text(
//                        text = "Box1",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = Color.White
//                    )
//                }
//            }
//
//            // Box 2
//            Box(
//                modifier = Modifier
//                    .size(150.dp)
//                    .zIndex(1f)
//                    .padding(20.dp)
//                    .background(Color.Black)
//                    .onGloballyPositioned { coordinates ->
////                        box2Position = coordinates.positionInParent()
//                        box2Rect = coordinates.boundsInWindow()
//                    }
//                    .offset { IntOffset(offsetX2.roundToInt(), offsetY2.roundToInt()) }
//                    .pointerInput(Unit){
//                        detectTapGestures {
//                            Log.e("testkuco2", "x2 = ${box2Rect.topLeft.x}, y2 = ${box2Rect.topLeft.y}")
//                        }
//                    }
//            )
//        }
//    }
//}
//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun ReadScreen(navController: NavController) {
//    val readViewModel: ReadViewModel = viewModel()
//    var draggedItem by remember { mutableStateOf<String?>(null) }
//
//    val cardQuizPositions = remember { mutableStateOf(listOf<Offset>()) }
//
//    val cardQuizItems = remember { mutableStateOf(listOf<String?>(null, null)) }
//    val cardQuiz2Bounds = remember { mutableStateOf<Rect?>(null) }
//    var offsetX1 by remember { mutableStateOf(0f) }
//    var offsetY1 by remember { mutableStateOf(0f) }
//
//
//
//    var box1Rect by remember { mutableStateOf(Rect.Zero) }
//    var box2Rect by remember { mutableStateOf(Rect.Zero) }
//
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        GradientQuiz(
//            navController = navController,
//            headerText = "Learn to Read",
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End,
//                ) {
//                    Text(
//                        "Level: 3",
//                        modifier = Modifier.padding(22.dp),
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 18.sp,
//                        color = ctextWhite
//                    )
//                }
//                MyShadowCard(
//                    modifier = Modifier
//                        .padding()
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.3f)
//                ) {
//
//                    FlowRow(
//                        modifier = Modifier.padding(12.dp),
//                    ) {
//
//
//                        Text(
//                            text = "The dog ",
//                            color = ctextBlack,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        CardQuiz(
//                            modifier = Modifier
//                                .onGloballyPositioned { coordinates ->
//                                    box2Rect = coordinates.boundsInWindow()
//                                }
//                        ) {
//                            Text(
//                                text = cardQuizItems.value[0] ?: "?",
//                                color = ctextWhite,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//                        Text(
//                            text = " and cat playing",
//                            color = ctextBlack,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        CardQuiz(
//                            modifier = Modifier
//                                .onGloballyPositioned { coordinates ->
//                                    cardQuiz2Bounds.value = coordinates.boundsInParent()
//                                }
//                        ) {
//                            Text(
//                                text = cardQuizItems.value[1] ?: "?",
//                                color = ctextWhite,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//                        Text(
//                            text = " ball",
//                            color = ctextBlack,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(30.dp))
//                HorizontalLine()
//                Spacer(modifier = Modifier.height(40.dp))
//                val listData = listOf("chases", "watch", "run")
//
//                DraggableAnswerCard(
//                    item = "chases",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
//                        .onGloballyPositioned {
//                            box1Rect = it.boundsInWindow()
//                        }
//                        .pointerInput(Unit){
//                            detectDragGestures(
//                                onDragStart = {
//
//                                },
//                                onDrag = {change, dragAmount ->
//                                    change.consume()
//                                    offsetX1 += dragAmount.x
//                                    offsetY1 += dragAmount.y
//                                },
//                                onDragEnd = {
//                                    val tesx = box1Rect.topLeft.x - offsetX1
//                                    val tesy = box1Rect.topLeft.y - offsetY1
//                                    val box1Center = Offset(
//                                        x = box1Rect.left + box1Rect.width / 2,
//                                        y = box1Rect.top + box1Rect.height / 2
//                                    )
//
//                                    if (box1Rect.overlaps(box2Rect)){
////                                        offsetX1 = box2Rect.topLeft.x - tesx
////                                        offsetY1 = box2Rect.topLeft.y - tesy
//                                    }
//                                }
//                            )
//                        }
//                )


//                DraggableAnswerCard(
//                    item = "chases",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
//                        .onGloballyPositioned {
//                            box1Rect = it.boundsInWindow()
//                        }.pointerInput(Unit){
//                            detectDragGestures(
//                                onDragStart = {
//
//                                },
//                                onDrag = {change, dragAmount ->
//                                    change.consume()
//                                    offsetX1 += dragAmount.x
//                                    offsetY1 += dragAmount.y
//                                },
//                                onDragEnd = {
//                                    val tesx = box1Rect.topLeft.x - offsetX1
//                                    val tesy = box1Rect.topLeft.y - offsetY1
//                                    val box1Center = Offset(
//                                        x = box1Rect.left + box1Rect.width / 2,
//                                        y = box1Rect.top + box1Rect.height / 2
//                                    )
////                                    val box2Left = box2Rect.left
////                                    val box2Top = box2Rect.top
////                                    val box2Right = box2Rect.right
////                                    val box2Bottom = box2Rect.bottom
//                                    if (box1Rect.overlaps(box2Rect)){
//                                        offsetX1 = box2Rect.topLeft.x - tesx
//                                        offsetY1 = box2Rect.topLeft.y - tesy
//                                    }
////                                    if (box1Center.x in box2Left..box2Right && box1Center.y in box2Top..box2Bottom) {
////                                        offsetX1 = box2Rect.topLeft.x - tesx
////                                        offsetY1 = box2Rect.topLeft.y - tesy
////                                }
//                            }
//                        )
//                    })


//                listData.forEach {
//                    DraggableAnswerCard(
//                        item = it,
//                        onDragStart = { draggedItem = it },
//                        onDragEnd = { isDropped ->
//                            if (isDropped) {
//                                val isDroppedInCardQuiz1 = cardQuiz1Bounds.value?.contains(Offset(offsetX, offsetY)) ?: false
//                                val isDroppedInCardQuiz2 = cardQuiz2Bounds.value?.contains(Offset(offsetX, offsetY)) ?: false
//
//                                if (isDroppedInCardQuiz1 && draggedItem != null) {
//                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
//                                        this[0] = draggedItem
//                                    }
//                                    draggedItem = null
//                                } else if (isDroppedInCardQuiz2 && draggedItem != null) {
//                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
//                                        this[1] = draggedItem
//                                    }
//                                    draggedItem = null
//                                }
//                            }
//                        },
//                        modifier = Modifier
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
//            }
//        }
//    }
//}