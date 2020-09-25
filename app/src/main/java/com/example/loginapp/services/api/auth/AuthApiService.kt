package com.example.loginapp.services.api.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.loginapp.model.Auth
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.CheckToken
import com.example.loginapp.model.Login
import com.example.loginapp.model.course.Course
import com.example.loginapp.repository.LoginRepository
import com.example.loginapp.repository.LoginRepository.token
import com.example.loginapp.services.api.courses.CoursesApi
import com.example.loginapp.util.PreferenceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiService {
    fun getRestEngine(): AuthApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        //val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl("https://movil-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            //.client(client)
            .build()
            .create(AuthApi::class.java)

    }

    private val authApi = Retrofit.Builder()
        .baseUrl("https://movil-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    suspend fun signin(data: Login): AuthResponse {
        return getRestEngine().signin(data.email, data.password)
    }

    suspend fun signup(data: Auth): AuthResponse {
        return getRestEngine().signup(data.email, data.password, data.username, data.name)
    }

    suspend fun restart(username: String): Any {
        return getRestEngine().restartDatabase(username)
    }


    /*suspend fun signup(data: Auth): MutableLiveData<AuthResponse> {
        val functionResponse = MutableLiveData<AuthResponse>()
        getRestEngine().signup(data.email, data.password, data.username, data.name).enqueue(object :
            Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body())
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        LoginRepository.setToken(loginResponse.token)
                        LoginRepository.setCredentials(data.username, data.password)
                        Log.d("MyOut", "OK isSuccessful token " + loginResponse.token)
                        functionResponse.value = loginResponse;
                    }
                } else {
                    Log.d("MyOut", "NOK  " + response.code())
                    Log.d("Err", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("MyOut", "Failure " + t.message)
            }
        })
            return functionResponse
    }*/

    suspend fun check(token: String): CheckToken{
        return getRestEngine().checkToken(token)
    }

}