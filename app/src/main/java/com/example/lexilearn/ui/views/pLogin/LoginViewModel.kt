package com.example.lexilearn.ui.views.pLogin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var isDialogOpen by mutableStateOf(false)

    fun testDialog(){
        isDialogOpen = true
        viewModelScope.launch {
            delay(5000)
            isDialogOpen = false
        }

    }
}