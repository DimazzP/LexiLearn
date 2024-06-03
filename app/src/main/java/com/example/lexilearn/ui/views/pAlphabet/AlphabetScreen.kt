package com.example.lexilearn.ui.views.pAlphabet

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.ui.components.BackButton

@Composable
fun AlphabetScreen(navController: NavController) {
    val context = navController.context
    val viewModel = AlphabetViewModel()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF6A69DB), Color(0xFF9B79F1)),
                        start = Offset(0f, 0f),
                        end = Offset(
                            Float.POSITIVE_INFINITY,
                            Float.POSITIVE_INFINITY
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackButton(onClick = { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Alphabet",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                items(26) { index ->
                    val letter = ('A' + index).toString()
                    Button(
                        onClick = {
                            viewModel.textToSpeech(context, letter)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White // Ganti dengan `cwhite` jika sudah didefinisikan
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .size(128.dp)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        Text(
                            text = letter,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun AlphabetScreenPreview() {
    AlphabetScreen(navController = rememberNavController())
}