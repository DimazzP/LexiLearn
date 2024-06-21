package com.example.lexilearn.ui.views.pDetailNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.lexilearn.R
import com.example.lexilearn.SharedViewModel
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextGray
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun NewsDetailScreen(
    navController: NavController,
    viewmodel: SharedViewModel = viewModel(),
) {
    val article by viewmodel.selectedArticle.observeAsState()

    GradientQuiz(
        navController = navController,
        headerText = stringResource(id = R.string.detail_news),
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(top = 60.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
                .fillMaxSize(), shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                if (article?.urlToImage == null) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_place_holder),
                        contentDescription = stringResource(id = R.string.strdefault),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    )
                } else {
                    Image(
                        painter = rememberImagePainter(data = article?.urlToImage),
                        contentDescription = stringResource(id = R.string.strdefault),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (article?.author != null) {
                    Text(
                        text = article?.author.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = cprimary
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (article?.title != null) {
                    Text(
                        text = article?.title.toString(),
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = ctextBlack
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                if (article?.description != null) {
                    Text(
                        text = article?.description.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ctextGray,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

            }
        }

    }
}
