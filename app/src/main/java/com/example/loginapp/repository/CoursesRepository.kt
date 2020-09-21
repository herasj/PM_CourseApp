package com.example.loginapp.repository
import com.example.loginapp.services.api.courses.CourseApiService
import com.example.loginapp.util.PreferenceProvider
class CourseRepository {

    private val apiService = CourseApiService()

    suspend fun getCourses() =  apiService.getCourses(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)

    suspend fun getCourseInfo(courseId: String) = apiService.getCourseInfo(PreferenceProvider.getUsername()!!, courseId, PreferenceProvider.getToken()!!)

    suspend fun createCourse() = apiService.createCourse(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)
}
