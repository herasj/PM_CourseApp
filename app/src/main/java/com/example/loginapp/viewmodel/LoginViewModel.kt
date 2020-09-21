package com.example.loginapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.loginapp.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository
    fun getLogged() = loginRepository.getLogged()
    fun getUsername() = loginRepository.getUsername()
    fun getPassword() = loginRepository.getPassword()
    fun setLogged(state: Boolean) {
        loginRepository.setLogged(state)
    }

    fun setCredentials(newUser: String, newPass: String) {
        loginRepository.setCredentials(newUser, newPass)
    }
}