//package com.example.lexilearn.ui.views.pQuiz.pRead
//
//import android.content.ClipDescription
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.draganddrop.dragAndDropSource
//import androidx.compose.foundation.draganddrop.dragAndDropTarget
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicText
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//
//
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draganddrop.DragAndDropEvent
//import androidx.compose.ui.draganddrop.DragAndDropTarget
//import androidx.compose.ui.draganddrop.mimeTypes
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Rect
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.White
//import androidx.compose.ui.input.pointer.consumeAllChanges
//import androidx.compose.ui.input.pointer.consumePositionChange
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.input.pointer.positionChange
//import androidx.compose.ui.layout.boundsInParent
//import androidx.compose.ui.layout.boundsInWindow
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.layout.positionInParent
//import androidx.compose.ui.layout.positionInWindow
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.unit.toSize
//import androidx.compose.ui.zIndex
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.lexilearn.ui.components.AutoSizeText
//import com.example.lexilearn.ui.components.CardQuiz
//import com.example.lexilearn.ui.components.DraggableAnswerCard
//import com.example.lexilearn.ui.components.GradientQuiz
//import com.example.lexilearn.ui.components.HorizontalLine
//import com.example.lexilearn.ui.components.MyShadowCard
//import com.example.lexilearn.ui.theme.cprimary
//import com.example.lexilearn.ui.theme.ctextBlack
//import com.example.lexilearn.ui.theme.ctextWhite
//import kotlin.math.absoluteValue
//import kotlin.math.roundToInt
//
////@Composable
////fun DragAndDropExample() {
////    var box1Items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
////    var box2Items by remember { mutableStateOf(listOf<String>()) }
////    var draggingItem by remember { mutableStateOf<String?>(null) }
////    var dragOffset by remember { mutableStateOf(Offset.Zero) }
////    var dragStartPosition by remember { mutableStateOf(Offset.Zero) }
////    var dragStartIndex by remember { mutableStateOf(-1) }
////    var box2Bounds by remember { mutableStateOf(Rect.Zero) }
////    var itemPositionOffset by remember { mutableStateOf(Offset.Zero) }
////
////    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
////        Row(modifier = Modifier.fillMaxWidth()) {
////            Box(
////                modifier = Modifier
////                    .weight(1f)
////                    .height(200.dp)
////                    .background(MaterialTheme.colorScheme.primary)
////            ) {
////                Column(modifier = Modifier.padding(start = 40.dp)) {
////                    box1Items.forEachIndexed { index, item ->
////                        var itemPosition by remember { mutableStateOf(Offset.Zero) }
////                        DraggableItem(
////                            text = item,
////                            onDragStart = { offset ->
////                                draggingItem = item
////                                dragStartIndex = index
////                                dragStartPosition = offset
////                                itemPositionOffset = itemPosition
////                            },
////                            onDrag = { offset -> dragOffset = offset },
////                            onDragEnd = { success ->
////                                val inBox2 = box2Bounds.contains(dragOffset + itemPositionOffset)
////                                if (success && inBox2 && draggingItem != null) {
////                                    box1Items = box1Items.toMutableList().apply { removeAt(dragStartIndex) }
////                                    box2Items = box2Items + draggingItem!!
////                                }
////                                draggingItem = null
////                                dragOffset = Offset.Zero
////                            },
////                            onPositioned = { position -> itemPosition = position }
////                        )
////                    }
////                }
////            }
////
////            Spacer(modifier = Modifier.width(16.dp))
////
////            Box(
////                modifier = Modifier
////                    .weight(1f)
////                    .height(300.dp)
////                    .background(MaterialTheme.colorScheme.secondary)
////                    .onGloballyPositioned { coordinates ->
////                        box2Bounds = coordinates.boundsInParent()
////                    }
////            ) {
////                Column {
////                    box2Items.forEach { item ->
////                        BasicText(text = item)
////                    }
////                }
////            }
////        }
////
////        draggingItem?.let { item ->
////            Box(
////                modifier = Modifier
////                    .offset {
////                        IntOffset(
////                            (dragOffset.x - dragStartPosition.x + itemPositionOffset.x).toInt(),
////                            (dragOffset.y - dragStartPosition.y + itemPositionOffset.y).toInt()
////                        )
////                    }
////                    .background(MaterialTheme.colorScheme.tertiary)
////                    .padding(8.dp)
////            ) {
////                BasicText(text = item)
////            }
////        }
////    }
////}
////
////@Composable
////fun DraggableItem(
////    text: String,
////    onDragStart: (Offset) -> Unit,
////    onDrag: (Offset) -> Unit,
////    onDragEnd: (Boolean) -> Unit,
////    onPositioned: (Offset) -> Unit
////) {
////    var isDragging by remember { mutableStateOf(false) }
////    var dragOffset by remember { mutableStateOf(Offset.Zero) }
////    var initialPosition by remember { mutableStateOf(Offset.Zero) }
////
////    Box(
////        modifier = Modifier
////            .padding(8.dp)
////            .pointerInput(Unit) {
////                detectDragGestures(
////                    onDragStart = { offset ->
////                        isDragging = true
////                        dragOffset = offset
////                        onDragStart(initialPosition)
////                    },
////                    onDragEnd = {
////                        isDragging = false
////                        onDragEnd(true)
////                    },
////                    onDragCancel = {
////                        isDragging = false
////                        onDragEnd(false)
////                    },
////                    onDrag = { change, dragAmount ->
////                        change.consume()
////                        dragOffset += dragAmount
////                        onDrag(dragOffset)
////                    }
////                )
////            }
////            .background(MaterialTheme.colorScheme.surface)
////            .onGloballyPositioned { coordinates ->
////                val position = coordinates.positionInParent()
////                initialPosition = position
////                onPositioned(position)
////            }
////    ) {
////        BasicText(text = text)
////    }
////}
//
//@Stable
//fun Int.toDp(density: Float): Dp = (this / density).dp
//
//@Composable
//fun ReadScreen() {
//    val d = LocalDensity.current
//    var offsetX1 by remember { mutableStateOf(0f) }
//    var offsetY1 by remember { mutableStateOf(0f) }
//    var offsetX2 by remember { mutableStateOf(0f) }
//    var offsetY2 by remember { mutableStateOf(0f) }
//    var box2Coordinates by remember { mutableStateOf(Offset.Zero) }
//    var box1Coordinates by remember { mutableStateOf(Offset.Zero) }
//    Log.e("offsetku2", "offsetX2: $offsetX2, offsetY2: $offsetY2")
//    Surface(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
//            // ini box1
//            Box(
//                modifier = Modifier
//                    .size(300.dp)
//                    .background(Color.Red)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .offset((offsetX1 / d.density).dp, (offsetY1 / d.density).dp)
//                        .size(100.dp)
//                        .zIndex(2f)
//                        .background(Color.Blue)
//                        .onGloballyPositioned { coordinates ->
//                            box1Coordinates = coordinates.localToRoot(Offset.Zero)
//                        }
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDrag = { change, dragAmount ->
//                                    change.consume()
//                                    offsetX1 += dragAmount.x
//                                    offsetY1 += dragAmount.y
//                                    Log.d("offsetku1", "x = $offsetX1, y = $offsetY1")
//                                },
//                                onDragEnd = {
//                                    val box1CoordinatesDp = with(density) {
//                                        Offset(
//                                            box2Coordinates.x.toDp().value,
//                                            box2Coordinates.y.toDp().value
//                                        )
//                                    }
//                                    offsetX1 = box2Coordinates.x
//                                    offsetY1 = box2Coordinates.y
//                                    Log.d(
//                                        "offsetku1",
//                                        "offsetX1: ${box1Coordinates.x}, offsetY1: ${box1Coordinates.y}"
//                                    )
//                                }
//                            )
//                        }
//                ) {
//                    Text(
//                        text = "Box1",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = White
//                    )
//                }
//            }
//
//
//            // ini box2
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Black)
//                    .zIndex(1f)
//                    .offset((offsetX2 / d.density).dp, (offsetY2 / d.density).dp)
//                    .onGloballyPositioned {
//
//                    }
//            )
//        }
//    }
//
////    var box1Items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
////    var box2Items by remember { mutableStateOf(listOf<String>()) }
////    var draggingItem by remember { mutableStateOf<String?>(null) }
////    var dragOffset by remember { mutableStateOf(Offset.Zero) }
////    var dragStartPosition by remember { mutableStateOf(Offset.Zero) }
////    var dragStartIndex by remember { mutableStateOf(-1) }
////    var box2Bounds by remember { mutableStateOf(Rect.Zero) }
////    var itemPositionOffset by remember { mutableStateOf(Offset.Zero) }
////
////    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
////        Row(modifier = Modifier.fillMaxWidth()) {
////            Box(
////                modifier = Modifier
////                    .weight(1f)
////                    .height(200.dp)
////                    .background(MaterialTheme.colorScheme.primary)
////            ) {
////                Column(modifier = Modifier.padding(start = 40.dp)) {
////                    box1Items.forEachIndexed { index, item ->
////                        var itemPosition by remember { mutableStateOf(Offset.Zero) }
////                        DraggableAnswerCard(
////                            text = item,
////                            onDragStart = { offset ->
////                                draggingItem = item
////                                dragStartIndex = index
////                                dragStartPosition = offset
////                                itemPositionOffset = itemPosition
////                            },
////                            onDrag = { offset -> dragOffset = offset },
////                            onDragEnd = { success ->
////                                val inBox2 = box2Bounds.contains(dragOffset + itemPositionOffset)
////                                if (success && inBox2 && draggingItem != null) {
////                                    box1Items = box1Items.toMutableList().apply { removeAt(dragStartIndex) }
////                                    box2Items = box2Items + draggingItem!!
////                                }
////                                draggingItem = null
////                                dragOffset = Offset.Zero
////                            },
////                            onPositioned = { position -> itemPosition = position }
////                        )
////                    }
////                }
////            }
////
////            Spacer(modifier = Modifier.width(16.dp))
////
////            Box(
////                modifier = Modifier
////                    .weight(1f)
////                    .height(300.dp)
////                    .background(MaterialTheme.colorScheme.secondary)
////                    .onGloballyPositioned { coordinates ->
////                        box2Bounds = coordinates.boundsInParent()
////                    }
////            ) {
////                Column {
////                    box2Items.forEach { item ->
////                        CardQuiz(text = item)
////                    }
////                }
////            }
////        }
////
////        draggingItem?.let { item ->
////            Box(
////                modifier = Modifier
////                    .offset {
////                        IntOffset(
////                            (dragOffset.x - dragStartPosition.x + itemPositionOffset.x).toInt(),
////                            (dragOffset.y - dragStartPosition.y + itemPositionOffset.y).toInt()
////                        )
////                    }
////                    .background(MaterialTheme.colorScheme.tertiary)
////                    .padding(8.dp)
////            ) {
////                BasicText(text = item)
////            }
////        }
////    }
//}
//
//@Composable
//fun DraggableAnswerCard(
//    text: String,
//    onDragStart: (Offset) -> Unit,
//    onDrag: (Offset) -> Unit,
//    onDragEnd: (Boolean) -> Unit,
//    onPositioned: (Offset) -> Unit
//) {
//    var isDragging by remember { mutableStateOf(false) }
//    var dragOffset by remember { mutableStateOf(Offset.Zero) }
//    var initialPosition by remember { mutableStateOf(Offset.Zero) }
//
//    Box(
//        modifier = Modifier
//            .padding(8.dp)
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragStart = { offset ->
//                        isDragging = true
//                        dragOffset = offset
//                        onDragStart(initialPosition)
//                    },
//                    onDragEnd = {
//                        isDragging = false
//                        onDragEnd(true)
//                    },
//                    onDragCancel = {
//                        isDragging = false
//                        onDragEnd(false)
//                    },
//                    onDrag = { change, dragAmount ->
//                        change.consume()
//                        dragOffset += dragAmount
//                        onDrag(dragOffset)
//                    }
//                )
//            }
//            .background(MaterialTheme.colorScheme.surface)
//            .onGloballyPositioned { coordinates ->
//                val position = coordinates.positionInParent()
//                initialPosition = position
//                onPositioned(position)
//            }
//    ) {
//        BasicText(text = text)
//    }
//}
//
//@Composable
//fun CardQuiz(text: String) {
//    Box(
//        modifier = Modifier
//            .padding(8.dp)
//            .background(MaterialTheme.colorScheme.surface)
//            .fillMaxWidth()
//            .height(50.dp)
//            .border(1.dp, MaterialTheme.colorScheme.onSurface)
//            .padding(8.dp)
//    ) {
//        BasicText(text = text)
//    }
//}
//
//
////@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
////@Composable
////fun ReadScreen(navController: NavController) {
////    val readViewModel: ReadViewModel = viewModel()
////    var draggedItem by remember { mutableStateOf<String?>(null) }
////
////    val cardQuizPositions = remember { mutableStateOf(listOf<Offset>()) }
////
////    val cardQuizItems = remember { mutableStateOf(listOf<String?>(null, null)) }
////    val cardQuiz1Bounds = remember { mutableStateOf<Rect?>(null) }
////    val cardQuiz2Bounds = remember { mutableStateOf<Rect?>(null) }
////    var offsetX by remember { mutableStateOf(0f) }
////    var offsetY by remember { mutableStateOf(0f) }
////    val callback = remember {
////        object : DragAndDropTarget {
////            override fun onDrop(event: DragAndDropEvent): Boolean {
////                Log.d("testkucoba", "pangggil drop")
////                return true
////            }
////        }
////    }
////    GradientQuiz(navController = navController, headerText = "Learn to Read") {
////
////        Box(
////            modifier = Modifier.fillMaxSize()
////        ) {
////            Column(
////                modifier = Modifier.fillMaxSize(),
////                horizontalAlignment = Alignment.CenterHorizontally,
////            ) {
////
////                Row(
////                    modifier = Modifier.fillMaxWidth(),
////                    horizontalArrangement = Arrangement.End,
////                ) {
////                    Text(
////                        "Level: 3",
////                        modifier = Modifier.padding(22.dp),
////                        fontWeight = FontWeight.SemiBold,
////                        fontSize = 18.sp,
////                        color = ctextWhite
////                    )
////                }
////                MyShadowCard(
////                    modifier = Modifier
////                        .padding()
////                        .fillMaxWidth()
////                        .fillMaxHeight(0.3f)
////                ) {
////
////                    FlowRow(
////                        modifier = Modifier.padding(12.dp),
////                    ) {
////
////
////                        Text(
////                            text = "The dog ",
////                            color = ctextBlack,
////                            fontSize = 20.sp,
////                            fontWeight = FontWeight.Bold
////                        )
////                        CardQuiz(
////                            modifier = Modifier
////                                .onGloballyPositioned { coordinates ->
////                                    cardQuiz1Bounds.value = coordinates.boundsInParent()
////                                }
////                        ) {
////                            Text(
////                                text = cardQuizItems.value[0] ?: "?",
////                                color = ctextWhite,
////                                fontWeight = FontWeight.Bold,
////                                textAlign = TextAlign.Center,
////                                modifier = Modifier.fillMaxWidth()
////                            )
////                        }
////                        Text(
////                            text = " and cat playing",
////                            color = ctextBlack,
////                            fontSize = 20.sp,
////                            fontWeight = FontWeight.Bold
////                        )
////                        CardQuiz(
////                            modifier = Modifier
////                                .onGloballyPositioned { coordinates ->
////                                    cardQuiz2Bounds.value = coordinates.boundsInParent()
////                                }
////                        ) {
////                            Text(
////                                text = cardQuizItems.value[1] ?: "?",
////                                color = ctextWhite,
////                                fontWeight = FontWeight.Bold,
////                                textAlign = TextAlign.Center,
////                                modifier = Modifier.fillMaxWidth()
////                            )
////                        }
////                        Text(
////                            text = " ball",
////                            color = ctextBlack,
////                            fontSize = 20.sp,
////                            fontWeight = FontWeight.Bold
////                        )
////                    }
////                }
////                CardQuiz(
////                    modifier = Modifier.dragAndDropTarget( shouldStartDragAndDrop = { event ->
////                        event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
////                    }, target = callback)
////                ) {
////                    Text(
////                        text = cardQuizItems.value[1] ?: "?",
////                        color = ctextWhite,
////                        fontWeight = FontWeight.Bold,
////                        textAlign = TextAlign.Center,
////                        modifier = Modifier.fillMaxWidth()
////                    )
////                }
////                Box(modifier = Modifier.dragAndDropSource {
////                    detectTapGestures(onLongPress = {
////                        // Transfer data here.
////                    })
////                }) {
////                    Text(text = "test aaja kok")
////                }
////                Spacer(modifier = Modifier.height(30.dp))
////                HorizontalLine()
////                Spacer(modifier = Modifier.height(40.dp))
////                val listData = listOf("chases", "watch", "run")
////
////                listData.forEach {
////                    DraggableAnswerCard(
////                        item = it,
////                        onDragStart = { draggedItem = it },
////                        onDragEnd = { isDropped ->
////                            if (isDropped) {
////                                val isDroppedInCardQuiz1 = cardQuiz1Bounds.value?.contains(Offset(offsetX, offsetY)) ?: false
////                                val isDroppedInCardQuiz2 = cardQuiz2Bounds.value?.contains(Offset(offsetX, offsetY)) ?: false
////
////                                if (isDroppedInCardQuiz1 && draggedItem != null) {
////                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
////                                        this[0] = draggedItem
////                                    }
////                                    draggedItem = null
////                                } else if (isDroppedInCardQuiz2 && draggedItem != null) {
////                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
////                                        this[1] = draggedItem
////                                    }
////                                    draggedItem = null
////                                }
////                            }
////                        },
////                        modifier = Modifier
////                    )
////                    Spacer(modifier = Modifier.height(20.dp))
////                }
////            }
////        }
////    }
////}