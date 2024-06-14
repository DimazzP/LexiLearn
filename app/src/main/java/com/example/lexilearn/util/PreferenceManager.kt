package com.example.lexilearn.util

import android.content.Context
import android.content.SharedPreferences
import com.example.lexilearn.data.auth.model.AuthData
import com.example.lexilearn.domain.auth.model.User

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
            setStringPreference(ConstVar.USER_NAME, it.user.name)
            setStringPreference(ConstVar.USER_EMAIL, it.user.email)
            setStringPreference(ConstVar.USER_PHOTO, it.user.photo)
            setStringPreference(ConstVar.USER_CREATED_AT, it.user.createdAt)
        }
    }

    fun setUserPref(userItem: User) {
        userItem.let {
            setStringPreference(ConstVar.USER_NAME, it.name)
            setStringPreference(ConstVar.USER_EMAIL, it.email)
            setStringPreference(ConstVar.USER_PHOTO, it.photo)
            setStringPreference(ConstVar.USER_CREATED_AT, it.createdAt)
        }
    }

    fun clearAllPreferences() {
        editor.remove(ConstVar.TOKEN_KEY)
        editor.remove(ConstVar.USER_NAME)
        editor.remove(ConstVar.USER_EMAIL)
        editor.remove(ConstVar.USER_PHOTO)
        editor.remove(ConstVar.USER_CREATED_AT)
        editor.apply()
    }

    val getToken = prefs.getString(ConstVar.TOKEN_KEY, "") ?: ""
    val getName = prefs.getString(ConstVar.USER_NAME, "") ?: ""
    val getEmail = prefs.getString(ConstVar.USER_EMAIL, "") ?: ""
    val getPhoto = prefs.getString(ConstVar.USER_PHOTO, "") ?: ""
    val getCreatedAt = prefs.getString(ConstVar.USER_CREATED_AT, "") ?: ""
}