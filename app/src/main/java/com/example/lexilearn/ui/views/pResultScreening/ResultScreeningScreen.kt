package com.example.lexilearn.ui.views.pResultScreening

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.GradientScreening
import com.example.lexilearn.ui.theme.ctextGray
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.domain.screening.model.ScreeningAnswerModel
import com.example.lexilearn.ui.components.CustomButton
import com.example.lexilearn.ui.theme.cprimary
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.cwhite

@Composable
fun ResultScreeningScreen(navController: NavController, result: Int) {
    val message = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(true) {
        when(result) {
            in 0..20 -> message.value = context.getString(R.string.verylowDyslexia)
            in 21..40 -> message.value = context.getString(R.string.lowDyslexia)
            in 41..60 -> message.value = context.getString(R.string.moderateDyslexia)
            in 61..80 -> message.value = context.getString(R.string.highDyslexia)
            in 81..100 -> message.value = context.getString(R.string.veryhighDyslexia)
        }
    }

    GradientScreening(
        backButton = { navController.popBackStack() },
        headerText = stringResource(id = R.string.rescreentitle),
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            val (lineRef, boxRef, buttonRef) = createRefs()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(ctextGray)
                .constrainAs(lineRef) {
                    top.linkTo(parent.top)
                }
            )
            Box(modifier = Modifier.constrainAs(boxRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                ConstraintLayout(modifier = Modifier.padding(bottom = 24.dp)) {
                    val (topRef, bottomRef) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(cwhite)
                            .constrainAs(topRef) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "Dyslexia",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = ctextBlack
                            )
                            Text(
                                text = "${result}%",
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                color = cprimary,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                            .offset(y = (-40).dp)
                            .background(cwhite, shape = RoundedCornerShape(16.dp))
                            .constrainAs(bottomRef) {
                                top.linkTo(topRef.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = message.value,
                                    fontSize = 16.sp,
                                    color = ctextBlack,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 24.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.rescreendesc),
                                    fontSize = 14.sp,
                                    color = ctextGray,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)
                                )
                        }
                    }
                }
            }
            CustomButton(
                text = stringResource(id = R.string.close),
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .constrainAs(buttonRef) {
                        bottom.linkTo(parent.bottom, margin = 12.dp)
                        end.linkTo(parent.end, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        width = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Preview
@Composable
fun ResultScreeningScreenPreview() {
    val navController = rememberNavController()
    ResultScreeningScreen(navController = navController, result = 50)
}
