package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun MyShadowCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .background(cwhite, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        content()
    }
}
@Preview
@Composable
fun MyShadowCardPreview() {
    MyShadowCard {
        // Placeholder content for the preview
        Text("Shadow Card Content")
    }
}
