package com.example.loginapp.model.professor

data class DetailProfessor (
    var course_id: Int = 0,
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var phone: String = "",
    var city: String = "",
    var country: String = "",
    var birthday: String = ""
)