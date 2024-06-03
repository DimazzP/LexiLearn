package com.example.lexilearn.ui.components
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonHome(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White // Ganti dengan `cwhite` jika sudah didefinisikan
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        content()
    }
}
@Preview
@Composable
fun ButtonHomePreview() {
    ButtonHome(onClick = {}) {
        Text(text = "Button Text")
    }
}
