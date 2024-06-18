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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lexilearn.R
import com.example.lexilearn.ui.theme.ctextGray


@Composable
fun CardScreening(question: String, selectedOption: String?, onOptionSelected: (String) -> Unit) {

    // r.string
    val textYes = stringResource(id = R.string.screenyes)
    val textNo = stringResource(id = R.string.screenno)
    val textNotSure = stringResource(id = R.string.screennotsure)

    val keyYes = "yes"
    val keyNo = "no"
    val keyNotSure = "not_sure"

    val getSelect = selectedOption ?: keyNo
    var selectedOption by remember { mutableStateOf(getSelect) }

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
                text = question,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.screenselect),
                fontSize = 14.sp,
                color = ctextGray,
                modifier = Modifier.padding(bottom = 4.dp, top = 6.dp)
            )

            OptionItem(
                text = textYes,
                iconColor = Color(0xFF4CAF50),
                isSelected = selectedOption == keyYes,
                painter = painterResource(id = R.drawable.cl_check),
                onSelect = {
                    selectedOption = keyYes
                    onOptionSelected(keyYes)
                }
            )
            OptionItem(
                text = textNo,
                iconColor = Color(0xFFF44336),
                isSelected = selectedOption == keyNo,
                painter = painterResource(id = R.drawable.cl_close),
                onSelect = {
                    selectedOption = keyNo
                    onOptionSelected(keyNo)
                }
            )
            OptionItem(
                text = textNotSure,
                iconColor = Color(0xFFFFC107),
                isSelected = selectedOption == keyNotSure,
                painter = painterResource(id = R.drawable.cl_helper),
                onSelect = {
                    selectedOption = keyNotSure
                    onOptionSelected(keyNotSure)
                }
            )
        }
    }
}

@Preview
@Composable
fun CardScreeningPreview() {
    CardScreening(
        question = "Is this a screening question?",
        selectedOption = "", // Simulate a selected option
        onOptionSelected = {}
    )
}
