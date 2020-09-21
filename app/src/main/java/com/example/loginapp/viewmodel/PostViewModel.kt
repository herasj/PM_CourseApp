package com.example.loginapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.Post
import com.example.loginapp.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()
    private val posts = mutableListOf<Post>()
    val postsLiveData = MutableLiveData<List<Post>>()

    init {
        getPost()
    }

    fun getPosts() {
        viewModelScope.launch {
            posts.addAll(repository.getPosts())
            postsLiveData.postValue(posts)
        }
    }

    fun getPost() {
        viewModelScope.launch {
            val post = repository.getPost(posts.size + 1)
            posts.add(post)
            postsLiveData.postValue(posts)
        }
    }

    fun createPost(title: String, body: String){
        val newPost = Post(title,body)
        viewModelScope.launch {
            val post = repository.postAPost(newPost)
            posts.add(post)
            postsLiveData.postValue(posts)
        }
    }


}
