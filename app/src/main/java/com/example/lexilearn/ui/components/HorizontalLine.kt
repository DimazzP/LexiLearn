package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(Color(0xFFD4D4D4))
    )
}

@Preview
@Composable
fun HorizontalLinePreview() {
    HorizontalLine()
}
