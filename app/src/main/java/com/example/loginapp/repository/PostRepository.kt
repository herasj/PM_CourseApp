package com.example.loginapp.repository

import com.example.loginapp.model.Post
import com.example.loginapp.services.api.PostApiService

class PostRepository {

    private val apiService = PostApiService()

    suspend fun getPosts() = apiService.getPosts()

    suspend fun getPost(index: Int) = apiService.getPost(index)

    suspend fun postAPost(post: Post) = apiService.postAPost(post)
}
