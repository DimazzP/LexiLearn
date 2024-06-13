package com.example.lexilearn.util

import android.content.Context
import android.util.Patterns
import androidx.compose.ui.text.input.TextFieldValue
import com.example.lexilearn.R

@Throws(IllegalArgumentException::class)
fun TextFieldValue.isValidName(context: Context): Boolean {
    if(this.text.isNotEmpty() && this.text.matches(Regex("^[a-zA-Z]+( [a-zA-Z]+)*\$"))) {
        return true
    }

    throw IllegalArgumentException(context.getString(R.string.name_only_contains_letters_and_spaces))
}

@Throws(IllegalArgumentException::class)
fun TextFieldValue.isValidEmail(context: Context): Boolean {
    if(this.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this.text).matches()) {
        return true
    }

    throw IllegalArgumentException(context.getString(R.string.invalid_email))
}

@Throws(IllegalArgumentException::class)
fun TextFieldValue.isValidPassword(context: Context): Boolean {
    if(this.text.isNotEmpty() && this.text.length >= 8) {
        return true
    }

    throw IllegalArgumentException(context.getString(R.string.password_min_8_characters))
}