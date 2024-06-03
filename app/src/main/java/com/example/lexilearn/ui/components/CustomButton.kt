package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.ctextBlack

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = caccent // Background color
        ),
        modifier = modifier
            .border(2.dp, caccent, RoundedCornerShape(12.dp)) // Border color and shape
            .background(caccent, RoundedCornerShape(12.dp)) // Background color and shape
            .padding(8.dp) // Padding inside the button
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ctextBlack // Text color
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        text = "Click Me",
        onClick = {}
    )
}
