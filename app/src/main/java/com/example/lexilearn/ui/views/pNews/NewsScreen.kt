package com.example.lexilearn.ui.views.pNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.lexilearn.R
import com.example.lexilearn.SharedViewModel
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: SharedViewModel,
) {
    val newsState by viewModel.newsState.observeAsState()
    val errorState by viewModel.errorState.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    GradientQuiz(
        navController = navController,
        headerText = stringResource(id = R.string.news),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(top = 60.dp)) {
            if (isLoading) {
                LottieProgressDialog(isDialogOpen = true) {
                }
            } else {
                errorState?.let {
                    Text(text = "Error", modifier = Modifier.fillMaxSize())
                } ?: run {
                    newsState?.articles.let { news ->
                        LottieProgressDialog(isDialogOpen = false) {}
                        if (news != null) {
                            LazyColumn {
                                itemsIndexed(news) { index, article ->
                                    if(article.title!=null && article.title!="[Removed]"){
                                        Card(
                                            onClick = {
                                                viewModel.selectArticle(article)
                                                navController.navigate("detail_news")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                        ) {
                                            Row {
                                                if (article.urlToImage == null) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.ic_place_holder),
                                                        contentDescription = stringResource(id = R.string.strdefault),
                                                        modifier = Modifier
                                                            .width(80.dp)
                                                            .height(80.dp)
                                                            .padding(start = 10.dp)
                                                    )
                                                } else {
                                                    Image(
                                                        painter = rememberImagePainter(data = article.urlToImage),
                                                        contentDescription = stringResource(id = R.string.strdefault),
                                                        modifier = Modifier
                                                            .width(80.dp)
                                                            .height(80.dp)
                                                            .padding(start = 10.dp)
                                                    )
                                                }

                                                Column(modifier = Modifier.padding(start = 10.dp)) {
                                                    Text(
                                                        text = article.title.toString(),
                                                        fontSize = 16.sp,
                                                        maxLines = 2,
                                                        overflow = TextOverflow.Ellipsis,
                                                        fontWeight = FontWeight.Bold,
                                                        color = ctextBlack,
                                                    )
                                                    Text(
                                                        text = article.description.toString(),
                                                        fontSize = 14.sp,
                                                        maxLines = 2,
                                                        overflow = TextOverflow.Ellipsis,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = ctextGray,
                                                        modifier = Modifier.padding(top = 2.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

