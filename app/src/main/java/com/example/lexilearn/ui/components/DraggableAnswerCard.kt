package com.example.lexilearn.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.cwhite
import kotlin.math.roundToInt
import androidx.compose.runtime.*

@Composable
fun DraggableAnswerCard(
    item: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = cwhite,
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(2.dp)
                .width(280.dp)
                .height(60.dp)
                .border(2.dp, cprimary, RoundedCornerShape(16.dp))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item,
                    textAlign = TextAlign.Center,
                    color = ctextBlack,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}

//@Composable
//fun DraggableAnswerCard(
//    item: String,
//    modifier: Modifier = Modifier
//) {
//
//    Box(
//        modifier = modifier
//
//    ) {
//        Card(
//            colors = CardDefaults.cardColors(
//                containerColor = cwhite,
//            ),
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier
//                .padding(2.dp)
//                .width(280.dp)
//                .height(60.dp)
//                .border(2.dp, cprimary, RoundedCornerShape(16.dp))
//        ) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Text(
//                    text = item,
//                    textAlign = TextAlign.Center,
//                    color = ctextBlack,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 12.dp)
//                )
//            }
//        }
//    }
//}
//fun DraggableAnswerCard(
//    item: String,
//    onDragStart: () -> Unit,
//    onDragEnd: (isDropped: Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var offsetX by remember { mutableStateOf(0f) }
//    var offsetY by remember { mutableStateOf(0f) }
//    var dragging by remember { mutableStateOf(false) }
//    var initialOffsetX by remember { mutableStateOf(0f) }
//    var initialOffsetY by remember { mutableStateOf(0f) }
//
//    Box(
//        modifier = modifier
//            .zIndex(if (dragging) 1f else 0f)
//            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragStart = {
//                        dragging = true
//                        initialOffsetX = offsetX
//                        initialOffsetY = offsetY
//                        onDragStart()
//                    },
//                    onDrag = { change, dragAmount ->
//                        change.consume()
//                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y
//                    },
//                    onDragEnd = {
//                        dragging = false
//                        onDragEnd(false)
//                        offsetX = initialOffsetX
//                        offsetY = initialOffsetY
//                    }
//                )
//            }
//    ) {
//        Card(
//            colors = CardDefaults.cardColors(
//                containerColor = cwhite,
//            ),
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier
//                .padding(2.dp)
//                .width(280.dp)
//                .height(60.dp)
//                .border(2.dp, cprimary, RoundedCornerShape(16.dp))
//        ) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Text(
//                    text = item,
//                    textAlign = TextAlign.Center,
//                    color = ctextBlack,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 12.dp)
//                )
//            }
//        }
//    }
//}