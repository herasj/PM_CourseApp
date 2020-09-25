package com.example.loginapp.repository

import com.example.loginapp.services.api.students.StudentsApiService
import com.example.loginapp.util.PreferenceProvider

class StudentRepository {

    private val apiService = StudentsApiService()

    suspend fun getStudents(username: String, token: String) =
        apiService.getStudent(username, token)

    suspend fun getStudentInfo(username: String, studentId: String, token: String) =
        apiService.getStudentInfo(username, studentId, token)

    suspend fun createStudent(username: String, token: String) =
        apiService.createStudent(username, token)

}