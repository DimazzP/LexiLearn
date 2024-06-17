package com.example.lexilearn.ui.views.pQuiz.pSpell

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import com.example.lexilearn.domain.models.ModelAnswerRead
import com.example.lexilearn.domain.models.ModelSpell

class SpellViewModel: ViewModel() {
    var rectColumnAnswer = mutableStateOf(Rect.Zero)
    var showLoading by mutableStateOf(false)

    val cardSize = mutableStateMapOf<Int, Dp>()


    var dataQuiz = mutableStateListOf(
        ModelSpell(1, true, "?", showCard = false),
        ModelSpell(2, false, "?", showCard = false),
        ModelSpell(3, true, "?", showCard = false),
        ModelSpell(4, false, "e", showCard = false),
    )

    val listAnswer = mutableStateListOf(
        ModelAnswerRead(1, "a"),
        ModelAnswerRead(2, "c"),
        ModelAnswerRead(3, "d"),
        ModelAnswerRead(4, "k")
    )

    val quizXOffset = mutableStateMapOf<Int, Float>()

    val quizYOffset = mutableStateMapOf<Int, Float>()

    val boxRectDragable = mutableStateMapOf<Int, Rect>()

    val boxRectQuiz = mutableStateMapOf<Int, Rect>()

    val answerXOffset = mutableStateMapOf<Int, Float>()

    val answerYOffset = mutableStateMapOf<Int, Float>()

    val boxRectAnswer = mutableStateMapOf<Int, Rect>()
}