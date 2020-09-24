package com.example.loginapp.services.api.auth

import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.model.course.Course
import com.example.loginapp.services.api.courses.CoursesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiService {
    private val authApi = Retrofit.Builder()
        .baseUrl("https://movil-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    suspend fun signin(data: Login): AuthResponse {
        return authApi.signin(data)
    }
    suspend fun signup(data: Auth): AuthResponse {
        return authApi.signup(data)
    }
}