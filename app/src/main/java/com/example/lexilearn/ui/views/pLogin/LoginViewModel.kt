package com.example.lexilearn.ui.views.pLogin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
}