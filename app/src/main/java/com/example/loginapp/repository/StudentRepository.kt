package com.example.loginapp.repository
import com.example.loginapp.services.api.students.StudentsApiService
import com.example.loginapp.util.PreferenceProvider

class StudentRepository {

    private val apiService = StudentsApiService()

    suspend fun getStudents() = apiService.getStudent(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)

    suspend fun getStudentInfo(studentId: String) = apiService.getStudentInfo(PreferenceProvider.getUsername()!!, studentId , PreferenceProvider.getToken()!!)

    suspend fun createStudent() = apiService.createStudent(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)

}