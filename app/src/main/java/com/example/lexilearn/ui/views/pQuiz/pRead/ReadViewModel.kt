package com.example.lexilearn.ui.views.pQuiz.pRead

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.domain.models.ModelAnswerRead
import com.example.lexilearn.domain.models.ModelWords
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.util.parseToQuizQuestion
import kotlinx.coroutines.launch

class ReadViewModel : ViewModel() {
    var rectColumnAnswer = mutableStateOf(Rect.Zero)
    var showLoading by mutableStateOf(false)

    val cardWidth: SnapshotStateMap<Int, Dp> = mutableStateMapOf()
    val cardHeight: SnapshotStateMap<Int, Dp> = mutableStateMapOf()

    private val _quiz = MutableLiveData<List<ModelWords>>(null)
    val dataQuiz: LiveData<List<ModelWords>> = _quiz

    private val _answer = MutableLiveData<List<ModelAnswerRead>>(null)
    val dataAnswer: LiveData<List<ModelAnswerRead>> = _answer

    private val _answerList = MutableLiveData<List<String>>(null)
    val answerList: LiveData<List<String>> = _answerList

    val quizXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val quizYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectDragable: SnapshotStateMap<Int, Rect> = mutableStateMapOf()
    val boxRectQuiz: SnapshotStateMap<Int, Rect> = mutableStateMapOf()

    val answerXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val answerYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectAnswer: SnapshotStateMap<Int, Rect> = mutableStateMapOf()

    fun getQuiz(data: QuizModel) {
        Log.d("ReadViewModel", "getQuiz: $data")
        viewModelScope.launch {
            val questions = data.question.parseToQuizQuestion()
            val quizData = questions.mapIndexed { index, question ->
                ModelWords(
                    id = index,
                    type = question === "?",
                    data = question,
                    showCard = false
                )
            }

            val answerData = data.answerList.mapIndexed { index, answer ->
                ModelAnswerRead(
                    id = index,
                    data = answer
                )
            }

            _quiz.value = quizData
            _answer.value = answerData
        }
    }
}