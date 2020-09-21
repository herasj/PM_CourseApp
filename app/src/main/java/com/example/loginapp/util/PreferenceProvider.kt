package com.example.loginapp.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider {
    companion object {
        private lateinit var preference: SharedPreferences
        fun initialize(context: Context) {
            preference = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
        }

        fun setValue(value: Boolean) {
            preference.edit().putBoolean("logged", value).apply()
        }

        fun getValue(): Boolean? {
            return preference.getBoolean("logged", false)
        }

        fun setCredentials(username: String, password: String) {
            preference.edit().putString("username", username).apply()
            preference.edit().putString("password", password).apply()
        }

        fun getUsername(): String? {
            return preference.getString("username", "N/A")
        }

        fun getPassword(): String? {
            return preference.getString("password", "N/A")
        }
    }
}