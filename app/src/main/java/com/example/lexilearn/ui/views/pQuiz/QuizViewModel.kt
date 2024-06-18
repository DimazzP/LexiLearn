package com.example.lexilearn.ui.views.pQuiz

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import kotlinx.coroutines.launch
import java.util.Locale

class QuizViewModel(private val useCase: QuizUseCase, private val context: Context) : ViewModel() {
    var showLoading by mutableStateOf(false)
    var quizState by mutableIntStateOf(0)

    private val _quiz = MutableLiveData<ApiResponse<List<QuizModel>>>(null)
    val quiz: LiveData<ApiResponse<List<QuizModel>>> = _quiz

    private val _answeredQuiz = MutableLiveData<List<AnsweredQuizModel>>(null)
    val answeredQuiz: LiveData<List<AnsweredQuizModel>> = _answeredQuiz

    val selectedChoice = mutableStateListOf<String>()


    fun getQuiz() {
        viewModelScope.launch {
            useCase.getQuiz().collect {
                _quiz.value = it
            }
        }
    }

    fun nextQuiz(questionId: Int, choice: List<String>, send: Boolean) {
        _answeredQuiz.value = _answeredQuiz.value?.toMutableList()?.apply {
            add(AnsweredQuizModel(questionId, choice))
        } ?: listOf(AnsweredQuizModel(questionId, choice))

        selectedChoice.clear()

        if (send) {
            sendQuiz()
        } else {
            quizState++
        }
    }

    fun sendQuiz() {
        Log.d("QuizViewModel", "sendQuiz: ${_answeredQuiz.value}")
    }

    private var textToSpeech: TextToSpeech? = null

    fun playSound(text: String, speed: Float) {
        textToSpeech?.isSpeaking?.let { isSpeaking ->
            if (isSpeaking) {
                textToSpeech?.stop()
            }
        }

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(speed)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                    )
                }
            }
        }
    }
}