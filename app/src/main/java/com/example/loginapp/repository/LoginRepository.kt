package com.example.loginapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginapp.util.PreferenceProvider

object LoginRepository {
    var logged = MutableLiveData<Boolean>()
    var stateLogged : Boolean = false
    var username = MutableLiveData<String>()
    var stateUsername : String = "N/A"
    var password = MutableLiveData<String>()
    var statePassword : String = "N/A"
    //Token
    var token = MutableLiveData<String>()
    var stateToken : String = "N/A"

    var email = MutableLiveData<String>()
    var stateEmail : String = "N/A"
    init {
        stateLogged = PreferenceProvider.getValue()!!
        logged.value = stateLogged;
        stateUsername = PreferenceProvider.getUsername()!!
        username.value = stateUsername
        statePassword = PreferenceProvider.getPassword()!!
        password.value = statePassword
        stateToken = PreferenceProvider.getToken()!!
        token.value = stateToken
        stateEmail = PreferenceProvider.getEmail()!!
        email.value = stateEmail
    }

    fun getLogged() = logged as LiveData<Boolean>

    fun setLogged(state: Boolean){
        stateLogged = state
        logged.value = stateLogged;
        PreferenceProvider.setValue(state)
    }

    fun getUsername() = username as LiveData<String>
    fun getPassword() = password as LiveData<String>

    fun setCredentials(newUser: String, newPass: String){
        stateUsername = newUser
        username.value = stateUsername;
        statePassword = newPass
        password.value = statePassword;
        PreferenceProvider.setCredentials(newUser, newPass)
    }

    fun getToken() = token as LiveData<String>
    fun setToken(newToken: String){
        stateToken = newToken
        token.value = stateToken;
        Log.d("SetToken", stateToken)
        PreferenceProvider.setToken("Bearer ${newToken}")
    }

    fun getEmail() = email as LiveData<String>
    fun setEmail(newEmail: String){
        stateEmail = newEmail
        email.value = stateEmail;
        PreferenceProvider.setEmail(newEmail)
    }
}