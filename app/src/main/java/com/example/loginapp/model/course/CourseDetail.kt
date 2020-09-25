package com.example.loginapp.model.course

import com.example.loginapp.model.professor.Professor
import com.example.loginapp.model.student.Student

data class CourseDetail (
    var name: String = "",
    var professor: Professor = Professor(),
    var students: List<Student> = listOf()
    )