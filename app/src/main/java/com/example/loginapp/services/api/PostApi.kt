package com.example.loginapp.services.api

import com.example.loginapp.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {
    @GET("posts/")
    suspend fun getPosts(): List<Post>


    @GET("posts/{index}/")
    suspend fun getPost(@Path("index") index: Int): Post


    @POST("posts/")
    suspend fun postAPost(@Body post: Post): Post
}
