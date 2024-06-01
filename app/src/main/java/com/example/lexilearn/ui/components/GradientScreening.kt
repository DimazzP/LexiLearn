package com.example.lexilearn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun GradientScreening(
    backButton: () -> Unit,
    headerText: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF6A69DB), Color(0xFF9B79F1)),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(
                        Float.POSITIVE_INFINITY,
                        Float.POSITIVE_INFINITY
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(80.dp)
                .align(alignment = Alignment.TopEnd)
                .clip(shape = RoundedCornerShape(bottomStart = 30.dp))
                .background(Color(0x1AEEEDFA)) // Warna transparan 10% #EEEDFA
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                val (backRef, textRef) = createRefs()
                IconButton(onClick = backButton, modifier = Modifier.constrainAs(backRef){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "icon close", tint = cwhite)
                }
                Text(
                    text = headerText,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier.constrainAs(textRef){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(x = (-200).dp, y = 100.dp)
                    .clip(shape = RoundedCornerShape(360.dp))
                    .background(Color(0x1AEEEDFA)) // Warna transparan 10% #EEEDFA
            )
        }
    }
    content()
}