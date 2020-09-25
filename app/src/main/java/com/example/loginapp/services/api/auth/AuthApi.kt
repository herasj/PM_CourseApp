package com.example.loginapp.services.api.auth

import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.CheckToken
import com.example.loginapp.model.Login
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AuthApi {
    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(
        @Field("email") email: String,
        @Field("password") pass: String,
        @Field("username") user: String,
        @Field("name") name: String
    ): AuthResponse

    @FormUrlEncoded
    @POST("signin")
    suspend fun signin(
        @Field("email") email: String,
        @Field("password") pass: String
    ): AuthResponse

    @POST("check/token")
    suspend fun checkToken(@Header("Authorization") token: String): CheckToken


}