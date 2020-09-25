package com.example.loginapp.repository

import android.util.Log
import com.example.loginapp.model.Auth
import com.example.loginapp.model.Login
import com.example.loginapp.services.api.auth.AuthApiService
import com.example.loginapp.util.PreferenceProvider
import retrofit2.HttpException

class AuthRepository {
    private val apiService = AuthApiService()
    private val authRepo = LoginRepository

    suspend fun signin(data: Login) = apiService.signin(data)

    suspend fun signup(data: Auth) = apiService.signup(data)

    suspend fun refreshToken() {
        val user:String= authRepo.getUsername().value!!;
        val token: String = authRepo.getToken().value!!;
        Log.d("user", user)
        Log.d("token", token)
        try {
            val response = apiService.check(token)
            if (!response.value!!.valid) {
                val newToken = this.signin(
                    Login(
                        LoginRepository.getEmail().value!!,
                        LoginRepository.getPassword().value!!
                    )
                )
               // PreferenceProvider.setToken("Bearer ${newToken.token}")
            }
        }catch (error: HttpException){
            Log.e("error1", error.message())
            Log.e("error2", error.response().toString())

        }

    }
}