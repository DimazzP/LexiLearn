package com.example.lexilearn.ui.views.pScreening

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.lexilearn.R
import com.example.lexilearn.domain.models.ModelScreening
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardScreening
import com.example.lexilearn.ui.components.GradientScreening
import com.example.lexilearn.ui.theme.cGray
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.ctextBlack
import com.example.lexilearn.ui.theme.ctextGray

@Composable
fun ScreeningScreen(navController: NavController) {
    val itemQuestion = remember {
        mutableStateListOf(
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
            ModelScreening(1, "Is there a family history of learning disorders?", 0),
        )
    }


    GradientScreening(
        backButton = { navController.popBackStack() },
        headerText = "Screening Test",
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
                itemsIndexed(itemQuestion) { index, item ->
                    CardScreening(onOptionSelected = { selectOption ->
                        itemQuestion[index].answer = selectOption
                    })
                }
            }
            Box(modifier = Modifier
                .padding(20.dp)
                .constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
                ButtonNext(
                    onclick = { },
                    text = "Send Test",
                    painter = painterResource(id = R.drawable.ic_send),
                    modifier = Modifier
                )
            }

//            Button(onClick = {},
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = caccent // Background color
//                ), modifier = Modifier
//                    .border(2.dp, caccent, RoundedCornerShape(12.dp))
//                    .background(caccent, RoundedCornerShape(12.dp))
//                    .constrainAs(buttonRef) {
//                        bottom.linkTo(parent.bottom)
//                        end.linkTo(parent.end)
//                    }) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(text = "Finish Test")
//                    Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "", tint = ctextBlack)
//                }
//            }
        }
    }
}