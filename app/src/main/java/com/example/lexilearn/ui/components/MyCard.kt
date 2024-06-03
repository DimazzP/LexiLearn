package com.example.lexilearn.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun MyCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cwhite,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth() // Atur sesuai kebutuhan Anda
    ) {
        content()
    }
}

@Preview
@Composable
fun MyCardPreview() {
    MyCard {
        // Placeholder content for the preview
        Text("Card Content")
    }
}
