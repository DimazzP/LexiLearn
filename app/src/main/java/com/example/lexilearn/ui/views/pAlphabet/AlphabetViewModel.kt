package com.example.lexilearn.ui.views.pAlphabet

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import java.util.Locale

class AlphabetViewModel : ViewModel() {
    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context, text: String) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
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