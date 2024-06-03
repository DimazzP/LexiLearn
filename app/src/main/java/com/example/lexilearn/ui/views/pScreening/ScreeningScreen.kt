package com.example.lexilearn.ui.views.pScreening

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardScreening
import com.example.lexilearn.ui.components.GradientScreening
import com.example.lexilearn.ui.theme.ctextGray

@Composable
fun ScreeningScreen(navController: NavController, viewModel: ScreeningViewModel = viewModel()) {

    GradientScreening(
        backButton = { navController.popBackStack() },
        headerText = stringResource(id = R.string.screentitle),
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            val (lineRef, lazyRef, buttonRef) = createRefs()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(ctextGray)
                .constrainAs(lineRef) {
                    top.linkTo(parent.top)
                })
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.constrainAs(lazyRef) {
                    top.linkTo(lineRef.bottom)
                }) {
                itemsIndexed(viewModel.itemQuestion) { index, item ->
                    CardScreening(
                        question = "${index + 1}. ${item.question}",
                        selectedOption = viewModel.itemQuestion[index].answer,
                        onOptionSelected = { selectOption ->
                            viewModel.itemQuestion[index].answer = selectOption
                        })
                }
                item {
                    ButtonNext(
                        onclick = { navController.navigate("resultscreening") },
                        text = stringResource(id = R.string.screensend),
                        painter = painterResource(id = R.drawable.ic_send),
                        modifier = Modifier
                            .padding(vertical = 30.dp, horizontal = 50.dp)
                            .fillMaxWidth()
                    )
                }
            }
            Box(modifier = Modifier
                .padding(20.dp)
                .constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
            }
        }
    }
}

@Preview
@Composable
fun ScreeningScreenPreview() {
    val navController = rememberNavController()
    val viewModel: ScreeningViewModel = viewModel()
    ScreeningScreen(navController = navController, viewModel = viewModel)
}
