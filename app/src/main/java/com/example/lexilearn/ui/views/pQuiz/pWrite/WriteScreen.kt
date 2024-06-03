package com.example.lexilearn.ui.views.pQuiz.pWrite

import DrawBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lexilearn.R
import com.example.lexilearn.ui.components.AutoSizeText
import com.example.lexilearn.ui.components.ButtonNext
import com.example.lexilearn.ui.components.CardQuiz
import com.example.lexilearn.ui.components.GradientQuiz
import com.example.lexilearn.ui.components.MyShadowCard
import com.example.lexilearn.ui.theme.ctextGray
import com.example.lexilearn.ui.theme.ctextWhite
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WriteScreen(navController: NavController, viewModel: WriteViewModel = viewModel()) {
    val minSize = 70.dp

    Surface(modifier = Modifier.fillMaxSize()) {
        GradientQuiz(
            navController = navController,
            headerText = stringResource(id = R.string.spelltitle),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                MyShadowCard(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Susun Kata", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_news),
                            contentDescription = "image",
                            modifier = Modifier.size(120.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            viewModel.dataQuiz.forEach { dt ->
                                CardQuiz(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .size(minSize)
                                ) {
                                    if (dt.type) {
                                        Text(
                                            text = dt.data,
                                            color = ctextWhite,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    } else {
                                        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                            Text(
                                                text = dt.data,
                                                color = ctextWhite,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ctextGray)
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            val (drawRef, textBoxRef) = createRefs()
                            DrawBox(
                                modifier = Modifier
                                    .size(300.dp, 300.dp)
                                    .constrainAs(drawRef) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    },
                                onPathReady = { path, size ->
                                    viewModel.pathReady.value = path
                                    viewModel.canvasSize.value = size
                                }
                            )
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(300.dp)
                                    .constrainAs(textBoxRef) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }) {
                                AutoSizeText(
                                    text = "c",
                                    fontFamily = FontFamily(
                                        Font(R.font.raleway_dots),
                                    ),
                                    textAlign = TextAlign.Center,
                                    color = ctextGray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        ButtonNext(
                            onclick = {
                                // jangan dihapus fungsi untuk mengubah coretan menjadi bitmap
//                                viewModel.pathReady.value?.let { path ->
//                                    val newBitmap = Bitmap.createBitmap(
//                                        viewModel.canvasSize.value.width.toInt(),
//                                        viewModel.canvasSize.value.height.toInt(),
//                                        Bitmap.Config.ARGB_8888
//                                    )
//                                    val canvas = android.graphics.Canvas(newBitmap)
//                                    canvas.drawColor(android.graphics.Color.WHITE)
//                                    canvas.drawPath(path, android.graphics.Paint().apply {
//                                        isAntiAlias = true
//                                        color = android.graphics.Color.BLACK
//                                        style = android.graphics.Paint.Style.STROKE
//                                        strokeJoin = android.graphics.Paint.Join.ROUND
//                                        strokeCap = android.graphics.Paint.Cap.ROUND
//                                        strokeWidth = 5f
//                                    })
//                                    viewModel.bitmap.value = newBitmap
//                                    Log.d("testcobaku", "masuk")
//                                }
                            },
                            text = stringResource(id = R.string.next),
                            painter = painterResource(id = R.drawable.ic_next),
                            modifier = Modifier
                                .padding(vertical = 30.dp, horizontal = 50.dp)
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // jangan dihapus contoh jika ingin menampilkan gambar
//                        viewModel.bitmap.value?.let {
//                            Log.d("testcobaku", "terisi")
//                            Image(
//                                bitmap = it.asImageBitmap(),
//                                contentDescription = null,
//                                modifier = Modifier.size(300.dp)
//                            )
//                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WriteScreenPreview() {
    val navController = rememberNavController()
    WriteScreen(navController = navController)
}
