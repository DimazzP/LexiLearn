package com.example.lexilearn.ui.views.pHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.di.KoinModules
import com.example.lexilearn.ui.components.AutoSizeText
import com.example.lexilearn.ui.components.ButtonHome
import com.example.lexilearn.ui.components.CircleAvatar
import com.example.lexilearn.ui.components.LottieProgressDialog
import com.example.lexilearn.ui.components.MyCard
import com.example.lexilearn.ui.theme.cbackground
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextWhite
import com.example.lexilearn.util.PreferenceManager
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import org.koin.compose.koinInject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    preferenceManager: PreferenceManager = koinInject(),
    viewModel: HomeViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
    val currentDate = LocalDate.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(cbackground)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(cprimary)
        ) {
            val (circleRef, titleRef, dateRef, settingRef) = createRefs()

            CircleAvatar(
                imageResId = R.drawable.ic_user,
                modifier = Modifier.constrainAs(circleRef) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                })
            Text(
                text = preferenceManager.getName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = ctextWhite,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(circleRef.top)
                    start.linkTo(circleRef.end, margin = 12.dp)
                }
            )
            Text(
                text = formatter.format(currentDate),
                fontSize = 14.sp,
                color = ctextWhite,
                modifier = Modifier.constrainAs(dateRef) {
                    top.linkTo(titleRef.bottom, margin = 4.dp)
                    start.linkTo(titleRef.start)
                }
            )
            IconButton(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.constrainAs(settingRef) {
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(circleRef.top)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_setting),
                    tint = ctextWhite,
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }
        }
        Box(
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
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
//            MyCard(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp)
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.homeprogress),
//                        color = cprimary,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        modifier = Modifier.align(Alignment.End)
//                    )
//                    val points = listOf(
//                        LineChartData.Point(1f, "Sun"),
//                        LineChartData.Point(2f, "Mon"),
//                        LineChartData.Point(1f, "Tue"),
//                        LineChartData.Point(7f, "Wed"),
//                        LineChartData.Point(4f, "Thu"),
//                        LineChartData.Point(6f, "Fri"),
//                        LineChartData.Point(9f, "Sat")
//                    )
//
//                    val lineData = remember {
//                        LineChartData(
//                            points = points,
//                            lineDrawer = SolidLineDrawer(2.dp, cprimary)
//                        )
//                    }
//                    LineChart(
//                        linesChartData = listOf(lineData),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(220.dp),
//                        animation = simpleChartAnimation(),
//                        pointDrawer = FilledCircularPointDrawer(),
//                        horizontalOffset = 1f,
//                    )
//                }
//            }
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val (quizRef, alphabetRef, screeningRef, newsRef) = createRefs()
                    ButtonHome(onClick = { navController.navigate("quiz") }, modifier = Modifier
                        .constrainAs(quizRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            width = Dimension.percent(0.5f)
                        }) {
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_quiz),
                                contentDescription = null,
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.homequiz),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = ctextBlack
                            )
                        }
                    }
                    ButtonHome(onClick = { navController.navigate("alphabet") }, modifier = Modifier
                        .constrainAs(alphabetRef) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(quizRef.bottom)
                            width = Dimension.percent(0.5f)
                        }) {
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_alphabet),
                                contentDescription = null,
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.homealphabet),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = ctextBlack
                            )
                        }
                    }
                    ButtonHome(onClick = { navController.navigate("screening") }, modifier = Modifier
                        .constrainAs(screeningRef) {
                            top.linkTo(quizRef.bottom)
                            start.linkTo(quizRef.start)
                            width = Dimension.percent(0.5f)
                        }) {
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_test),
                                contentDescription = null,
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            )
                            AutoSizeText(
                                text = stringResource(id = R.string.homescreening),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = ctextBlack,
                                maxLines = 1
                            )
                        }
                    }
                    ButtonHome(onClick = { navController.navigate("news") }, modifier = Modifier
                        .constrainAs(newsRef) {
                            top.linkTo(quizRef.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.percent(0.5f)
                        }) {
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_news),
                                contentDescription = null,
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.homenews),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = ctextBlack
                            )
                        }
                    }
                }
            }
        }
        LottieProgressDialog(isDialogOpen = viewModel.showLoading) {
            viewModel.showLoading = false
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}