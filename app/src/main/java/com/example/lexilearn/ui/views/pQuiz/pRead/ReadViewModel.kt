package com.example.lexilearn.ui.views.pQuiz.pRead

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import com.example.lexilearn.domain.models.ModelAnswerRead
import com.example.lexilearn.domain.models.ModelWords

class ReadViewModel: ViewModel() {
    var rectColumnAnswer = mutableStateOf(Rect.Zero)
    var showLoading by mutableStateOf(false)

    val cardWidth: SnapshotStateMap<Int, Dp> = mutableStateMapOf()
    val cardHeight: SnapshotStateMap<Int, Dp> = mutableStateMapOf()

    var dataQuiz: SnapshotStateList<ModelWords> = mutableStateListOf(
        ModelWords(1, false, "The dog ", showCard = false),
        ModelWords(2, true, "?", showCard = false),
        ModelWords(3, false, " and The Cat ", showCard = false),
        ModelWords(4, true, "?", showCard = false),
    )

    val listAnswer: SnapshotStateList<ModelAnswerRead> = mutableStateListOf(
        ModelAnswerRead(1, "chases"),
        ModelAnswerRead(2, "run"),
        ModelAnswerRead(3, "watches")
    )

    val quizXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val quizYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectDragable: SnapshotStateMap<Int, Rect> = mutableStateMapOf()
    val boxRectQuiz: SnapshotStateMap<Int, Rect> = mutableStateMapOf()

    val answerXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val answerYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectAnswer: SnapshotStateMap<Int, Rect> = mutableStateMapOf()
}