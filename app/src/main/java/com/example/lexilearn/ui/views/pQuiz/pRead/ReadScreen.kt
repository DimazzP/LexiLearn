package com.example.lexilearn.ui.views.pQuiz.pRead

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lexilearn.ui.components.*
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import com.mohamedrejeb.compose.dnd.rememberDragAndDropState

@Composable
fun ReadScreen(navController: NavController){
    val dragAndDropState = rememberDragAndDropState()

}

//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun ReadScreen(navController: NavController) {
//    val readViewModel: ReadViewModel = viewModel()
//    var draggedItem by remember { mutableStateOf<String?>(null) }
//
//    // State to keep track of the positions of CardQuiz
//    val cardQuizPositions = remember { mutableStateOf(listOf<Offset>()) }
//
//    // State to keep track of dropped items in CardQuiz
//    val cardQuizItems = remember { mutableStateOf(listOf<String?>(null, null)) }
//
//    GradientQuiz(navController = navController, headerText = "Learn to Read") {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
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
//                    FlowRow(
//                        modifier = Modifier.padding(12.dp),
//                    ) {
//                        val cardQuiz1Bounds = remember { mutableStateOf<Offset?>(null) }
//                        val cardQuiz2Bounds = remember { mutableStateOf<Offset?>(null) }
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
//                                    cardQuiz1Bounds.value = coordinates.boundsInParent().center
//                                }
//                                .pointerInput(Unit) {
//                                    detectDragGestures(
//                                        onDrag = { change, _ -> change.consume() },
//                                        onDragStart = {},
//                                        onDragEnd = {
//                                            cardQuiz1Bounds?.let { bounds ->
//                                                if (draggedItem != null) {
//                                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
//                                                        this[0] = draggedItem
//                                                    }
//                                                    draggedItem = null
//                                                }
//                                            }
//                                        }
//                                    )
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
//                                    cardQuiz2Bounds.value = coordinates.boundsInParent().center
//                                }
//                                .pointerInput(Unit) {
//                                    detectDragGestures(
//                                        onDrag = { change, _ -> change.consume() },
//                                        onDragStart = {},
//                                        onDragEnd = {
//                                            cardQuiz2Bounds?.let { bounds ->
//                                                if (draggedItem != null) {
//                                                    cardQuizItems.value = cardQuizItems.value.toMutableList().apply {
//                                                        this[1] = draggedItem
//                                                    }
//                                                    draggedItem = null
//                                                }
//                                            }
//                                        }
//                                    )
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
//
//                }
//                Spacer(modifier = Modifier.height(30.dp))
//                HorizontalLine()
//                Spacer(modifier = Modifier.height(40.dp))
//                val listData = listOf("chases", "watch", "run")
//                listData.forEach {
//                    DraggableAnswerCard(
//                        item = it,
//                        onDragStart = { draggedItem = it },
//                        onDragEnd = {
//                        },
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
//            }
//        }
//    }
//}