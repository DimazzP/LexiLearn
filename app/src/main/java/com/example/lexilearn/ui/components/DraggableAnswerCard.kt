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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.cwhite

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
                .border(2.dp, cprimary, RoundedCornerShape(16.dp))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AutoSizeText(
                    text = item,
                    textAlign = TextAlign.Center,
                    color = ctextBlack,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DraggableAnswerCardPreview() {
    DraggableAnswerCard(item = "Sample Answer")
}
