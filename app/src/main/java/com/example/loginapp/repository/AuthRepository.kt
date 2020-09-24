package com.example.loginapp.repository

import com.example.loginapp.model.Auth
import com.example.loginapp.model.Login
import com.example.loginapp.services.api.auth.AuthApiService

class AuthRepository {
    private val apiService = AuthApiService()

    suspend fun signin(data: Login) =  apiService.signin(data)

    suspend fun signup(data: Auth) = apiService.signup(data)

}