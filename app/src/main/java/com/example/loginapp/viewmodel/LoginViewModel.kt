package com.example.loginapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository
    private val authRepository = AuthRepository()

    fun setLogged(state: Boolean) {
        loginRepository.setLogged(state)
    }

    fun getLogged(): LiveData<Boolean> {
        return loginRepository.getLogged()
    }

    fun login(email: String, pass: String) {
        loginRepository.setEmail(email)
        loginRepository.setPassword(pass)
        viewModelScope.launch {
            val newLogin = Login(email, pass)
            Log.d("data", "$newLogin")
            val response = authRepository.signin(newLogin)
            Log.d("Login", "$response")
            loginRepository.setEmail(email)
//            loginRepository.setToken("Bearer ${response.token}")
        }
    }

    fun restart() {
        viewModelScope.launch {
            val response = authRepository.restartDatabase(loginRepository.getUsername().value!!)
            Log.d("Restart", "$response")
//            loginRepository.setToken("Bearer ${response.token}")
        }
    }

    fun setCredentials(newUser: String, newPass: String, email: String, name: String) {
        val newAuth = Auth(email, newPass, newUser, name)
        loginRepository.setUsername(newUser)
        loginRepository.setEmail(email)
        loginRepository.setPassword(newPass)

        viewModelScope.launch {
            Log.d("data", "$newAuth")
            Log.d("PreRequest","Hello:D")
            val response: AuthResponse = authRepository.signup(newAuth);
            Log.d("PostRequest","Hello:D")
            Log.d("Response","$response")
            loginRepository.setToken("Bearer ${response.token}")
            Log.d("PreferenceToken","${loginRepository.getToken().value}")
            Log.d("PreferenceUser","${loginRepository.getUsername().value}")
            Log.d("PreferenceEmail","${loginRepository.getEmail().value}")
        }

    }
}