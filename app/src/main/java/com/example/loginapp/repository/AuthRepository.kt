package com.example.loginapp.repository

import com.example.loginapp.model.Auth
import com.example.loginapp.model.Login
import com.example.loginapp.services.api.auth.AuthApiService
import com.example.loginapp.util.PreferenceProvider

class AuthRepository {
    private val apiService = AuthApiService()

    suspend fun signin(data: Login) = apiService.signin(data)

    suspend fun signup(data: Auth) = apiService.signup(data)

    suspend fun refreshToken() {
        val response = apiService.check(PreferenceProvider.getToken()!!)
        if (!response.valid) {
            val newToken = this.signin(
                Login(
                    PreferenceProvider.getEmail()!!,
                    PreferenceProvider.getPassword()!!
                )
            )
            PreferenceProvider.setToken("Bearer ${newToken.token}")
        }
    }
}