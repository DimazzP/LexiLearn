package com.example.lexilearn.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.*
import com.example.lexilearn.R

@Composable
fun LottieProgressDialog(isDialogOpen: Boolean, onDismissRequest: () -> Unit) {
    if (isDialogOpen) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = Color.White,
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_bar))
                    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
                    LottieAnimation(
                        composition,
                        progress,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = stringResource(id = R.string.wait), fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}