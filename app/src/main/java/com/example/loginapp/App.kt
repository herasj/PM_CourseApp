package com.example.loginapp

import android.app.Application
import android.util.Log
import com.example.loginapp.util.PreferenceProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("VideoViewModel","App onCreate")
        PreferenceProvider.initialize(this)
    }
}
