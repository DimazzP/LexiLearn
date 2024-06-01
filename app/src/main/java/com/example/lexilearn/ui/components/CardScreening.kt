package com.example.lexilearn.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lexilearn.R


@Composable
fun CardScreening(onOptionSelected: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFECEFF1))
                .padding(16.dp)
        ) {
            Text(
                text = "Is there a family history of learning disorders?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Select One",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OptionItem(
                text = "Yes",
                iconColor = Color(0xFF4CAF50),
                isSelected = selectedOption == "Yes",
                painter = painterResource(id = R.drawable.cl_check),
                onSelect = {
                    selectedOption = "Yes"
                    onOptionSelected(1)
                }
            )
            OptionItem(
                text = "No",
                iconColor = Color(0xFFF44336),
                isSelected = selectedOption == "No",
                painter = painterResource(id = R.drawable.cl_close),
                onSelect = {
                    selectedOption = "No"
                    onOptionSelected(2)
                }
            )
            OptionItem(
                text = "Don't Know",
                iconColor = Color(0xFFFFC107),
                isSelected = selectedOption == "Don't Know",
                painter = painterResource(id = R.drawable.cl_helper),
                onSelect = {
                    selectedOption = "Don't Know"
                    onOptionSelected(3)
                }
            )
        }
    }
}
