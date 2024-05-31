package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack

@Composable
fun CardQuiz(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .border(2.dp, ctextBlack, RoundedCornerShape(16.dp))
            .background(cprimary, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
//    Box(
////        colors = CardDefaults.cardColors(
////            containerColor = cprimary,
////        ),
////        shape = RoundedCornerShape(16.dp),
//        modifier = modifier
//            .border(2.dp, ctextBlack, RoundedCornerShape(16.dp)).background(cprimary)
//    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            content()
//        }
//    }
}

//@Composable
//fun CardQuiz(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = cprimary,
//        ),
//        shape = RoundedCornerShape(16.dp),
//        modifier = modifier
//            .padding(2.dp)
//            .width(80.dp)
//            .height(30.dp).border(2.dp, ctextBlack, RoundedCornerShape(16.dp))
//    ) {
//        Box(
//            contentAlignment = Alignment.Center, // Memastikan konten berada di tengah
//            modifier = Modifier.fillMaxSize() // Pastikan Box menggunakan ukuran penuh
//        ) {
//            content()
//        }
//    }
//}