package com.example.loginapp.services.api.auth

import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import retrofit2.http.*

interface AuthApi {
    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(@Body data: Auth): AuthResponse

    @FormUrlEncoded
    @POST("signin")
    suspend fun signin(@Body data: Login): AuthResponse

}