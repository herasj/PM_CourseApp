package com.example.loginapp.model

data class AuthResponse(
    var type:String ="",
    var token:String="",
    var username:String ="",
    var name:String ="",
    var email:String= ""
)