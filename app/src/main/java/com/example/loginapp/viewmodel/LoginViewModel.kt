package com.example.loginapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.Auth
import com.example.loginapp.model.Login
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository
    private val authRepository = AuthRepository()
    fun getLogged() = loginRepository.getLogged()
    fun getUsername() = loginRepository.getUsername()
    fun getPassword() = loginRepository.getPassword()


    fun setLogged(state: Boolean) {
        loginRepository.setLogged(state)
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            val newLogin = Login(email, pass)
            Log.d("data", "$newLogin")
            val response = authRepository.signin(newLogin)
            Log.d("Login", "$response")
            loginRepository.setEmail(email)
//            loginRepository.setToken("Bearer ${response.token}")
        }
    }

    fun setCredentials(newUser: String, newPass: String, email: String, name: String) {
        val newAuth = Auth(email, newPass, newUser, name)

        viewModelScope.launch {

            Log.d("data", "$newAuth")
            val response = authRepository.signup(newAuth);
            Log.d("Register", "$response")

        }
        loginRepository.setEmail(email)

    }
}