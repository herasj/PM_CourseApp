package com.example.loginapp.services.api

import com.example.loginapp.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PostApiService {
    private val postsApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApi::class.java)

    suspend fun getPosts(): List<Post> {
        return postsApi.getPosts()
    }

    suspend fun getPost(index: Int): Post {
        return postsApi.getPost(index)
    }

    suspend fun postAPost(post: Post): Post {
        return postsApi.postAPost(post)
    }

}