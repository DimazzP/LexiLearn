package com.example.lexilearn.ui.views.pQuiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.QuizUseCase
import com.example.lexilearn.domain.quiz.model.QuizModel
import kotlinx.coroutines.launch

class QuizViewModel(private val useCase: QuizUseCase) : ViewModel() {
    var showLoading by mutableStateOf(false)

    val cardWidth: SnapshotStateMap<Int, Dp> = mutableStateMapOf()
    val cardHeight: SnapshotStateMap<Int, Dp> = mutableStateMapOf()

    private val _quiz = MutableLiveData<ApiResponse<QuizModel>>(null)
    val quiz: LiveData<ApiResponse<QuizModel>> = _quiz

    fun getQuiz() {
        viewModelScope.launch {
            useCase.getQuiz().collect {
                _quiz.value = it
            }
        }
    }
    
    val quizXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val quizYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectDragable: SnapshotStateMap<Int, Rect> = mutableStateMapOf()
    val boxRectQuiz: SnapshotStateMap<Int, Rect> = mutableStateMapOf()

    val answerXOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()
    val answerYOffset: SnapshotStateMap<Int, Float> = mutableStateMapOf()

    val boxRectAnswer: SnapshotStateMap<Int, Rect> = mutableStateMapOf()
}