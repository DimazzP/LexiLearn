package com.example.lexilearn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun CircleAvatar(
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(cwhite)
            .border(2.dp, cwhite, CircleShape)
    )
}