package com.example.lexilearn.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lexilearn.ui.theme.caccent
import com.example.lexilearn.ui.theme.ctextWhite


@Composable
fun LoginTextButton(
    textHelper: String,
    textBtn: String,
    onclick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        append(textHelper)

        pushStringAnnotation(tag = "register", annotation = "register")
        withStyle(style = SpanStyle(color = Color(0xFFFFA500), textDecoration = TextDecoration.Underline)) {
            append(textBtn)
        }
        pop()
    }

    Text(
        text = annotatedText,
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onclick()
            },

        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = ctextWhite// Pastikan Anda mengganti ctextWhite dengan Color.White atau definisikan ctextWhite
    )
}
@Preview
@Composable
fun LoginTextButtonPreview() {
    LoginTextButton(
        textHelper = "Don't have an account? ",
        textBtn = "Register",
        onclick = { /* Mock click action */ }
    )
}
