package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lexilearn.R
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.ctextBlack

@Composable
fun ButtonNext(onclick: () -> Unit, text: String, painter: Painter, modifier: Modifier) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = caccent // Background color
        ), modifier = modifier
            .border(2.dp, caccent, RoundedCornerShape(12.dp))
            .background(caccent, RoundedCornerShape(12.dp))
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = ctextBlack
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painter,
                contentDescription = "",
                tint = ctextBlack
            )
        }
    }
}
