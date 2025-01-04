package com.training.graduation.screens.sharedprefrence

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("language_pref", Context.MODE_PRIVATE)

    fun saveLanguage(languageCode: String) {
        preferences.edit().putString("selected_language", languageCode).apply()
    }

    fun getLanguage(): String {
        return preferences.getString("selected_language", "en") ?: "en" // Default to English
    }
}
