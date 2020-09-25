package com.example.loginapp.repository

import android.util.Log
import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.services.api.auth.AuthApiService
import com.example.loginapp.util.PreferenceProvider
import retrofit2.HttpException

class AuthRepository {
    private val apiService = AuthApiService()
    private val authRepo = LoginRepository

    suspend fun signin(data: Login):AuthResponse = apiService.signin(data)

    suspend fun signup(data: Auth) = apiService.signup(data)

    suspend fun refreshToken(token: String) = apiService.check(token)

    suspend fun restartDatabase(username: String) = apiService.restart(username)

}