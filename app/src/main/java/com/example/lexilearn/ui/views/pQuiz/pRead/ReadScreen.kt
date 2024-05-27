package com.example.lexilearn.ui.views.pQuiz.pRead

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lexilearn.ui.components.AnswerCard
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.HorizontalLine
import com.example.lexilearn.ui.components.MyCard
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite

@Composable
fun ReadScreen(navController: NavController) {
    val readViewModel: ReadViewModel = viewModel()
    GradientQuiz(navController = navController, headerText = "Learn to Read") {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    "Level: 3",
                    modifier = Modifier.padding(22.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = ctextWhite
                )
            }
            MyShadowCard(modifier = Modifier
                .padding()
                .fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "The dog ",
                        color = ctextBlack,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    CardQuiz {
                        Text(
                            text = "?",
                            color = ctextWhite,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
            HorizontalLine()
            val listData = listOf("chases", "watch", "run")
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
            ) {
                items(listData) { item ->
                    AnswerCard(item = item)
                }
            }
        }
    }
}