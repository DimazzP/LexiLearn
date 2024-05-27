package com.example.lexilearn.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun AnswerCard(item: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cwhite,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(2.dp)
            .width(280.dp)
            .height(60.dp)
            .border(2.dp, cprimary, RoundedCornerShape(16.dp))
    ) {
        Box(
            contentAlignment = Alignment.Center, // Memastikan konten berada di tengah
            modifier = Modifier.fillMaxSize() // Pastikan Box menggunakan ukuran penuh
        ) {
            AutoSizeText(
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