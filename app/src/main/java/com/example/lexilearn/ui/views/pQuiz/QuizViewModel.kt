package com.example.lexilearn.ui.views.pQuiz

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.lib.ApiResponse
import com.example.lexilearn.domain.quiz.QuizUseCase
import com.example.lexilearn.domain.quiz.model.AnsweredQuizModel
import com.example.lexilearn.domain.quiz.model.QuizModel
import com.example.lexilearn.domain.quiz.model.QuizResultModel
import kotlinx.coroutines.launch
import java.util.Locale

class QuizViewModel(private val useCase: QuizUseCase, private val context: Context) : ViewModel() {
    var showLoading by mutableStateOf(false)
    var quizState by mutableIntStateOf(0)

    private val _quiz = MutableLiveData<ApiResponse<List<QuizModel>>>(null)
    val quiz: LiveData<ApiResponse<List<QuizModel>>> = _quiz

    private val _quizAnswerResult = MutableLiveData<ApiResponse<QuizResultModel>>(null)
    val quizAnswerResult: LiveData<ApiResponse<QuizResultModel>> = _quizAnswerResult
    
    private val _answeredQuiz = mutableListOf<AnsweredQuizModel>()

    val selectedChoice = mutableStateListOf<String>()


    fun getQuiz() {
        viewModelScope.launch {
            useCase.getQuiz().collect {
                _quiz.value = it
            }
        }
    }

    fun nextQuiz(questionId: Int, choice: List<String>, send: Boolean) {
        _answeredQuiz.add(AnsweredQuizModel(questionId, choice.toList()))
        selectedChoice.clear()

        if (send) {
            sendQuiz()
        } else {
            quizState++
        }
    }

    fun sendQuiz() {
        viewModelScope.launch {
            _answeredQuiz.let { answers ->
                useCase.sendAnswers(answers).collect {
                    _quizAnswerResult.value = it
                }
            }
        }
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