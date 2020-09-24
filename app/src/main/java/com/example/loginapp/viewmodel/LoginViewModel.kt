package com.example.loginapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.loginapp.model.Auth
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository
    private val authRepository = AuthRepository()
    fun getLogged() = loginRepository.getLogged()
    fun getUsername() = loginRepository.getUsername()
    fun getPassword() = loginRepository.getPassword()



    fun setLogged(state: Boolean) {
        loginRepository.setLogged(state)
    }

    fun setCredentials(newUser: String, newPass: String, email: String, name: String, ) {
        loginRepository.setCredentials(newUser, newPass)
    }
}