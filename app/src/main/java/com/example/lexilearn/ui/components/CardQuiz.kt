package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
}

@Preview
@Composable
fun CardQuizPreview() {
    CardQuiz(modifier = Modifier.padding(16.dp)) {
        Text("Quiz Card Content")
    }
}
