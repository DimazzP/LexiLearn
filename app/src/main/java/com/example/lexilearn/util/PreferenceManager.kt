package com.example.lexilearn.util

import android.content.Context
import android.content.SharedPreferences
import com.example.lexilearn.data.auth.model.AuthData

class PreferenceManager(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(ConstVar.PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setStringPreference(prefKey: String, value: String) {
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun setLoginPref(userItem: AuthData) {
        userItem.let {
            setStringPreference(ConstVar.TOKEN_KEY, it.accessToken)
        }
    }

    fun clearAllPreferences() {
        editor.remove(ConstVar.TOKEN_KEY)
        editor.apply()
    }

    val getToken = prefs.getString(ConstVar.TOKEN_KEY, "") ?: ""
}