package com.example.loginapp.repository
import android.util.Log
import com.example.loginapp.model.course.Course
import com.example.loginapp.services.api.courses.CourseApiService
import com.example.loginapp.util.PreferenceProvider
class CourseRepository {

    private val apiService = CourseApiService()
    suspend fun getCourses(username: String, token: String) : List<Course> {
        return apiService.getCourses(username, token)
    }

    suspend fun getCourseInfo(username: String, token: String, courseId: String) = apiService.getCourseInfo(username, courseId,token)

    suspend fun createCourse(username: String, token: String) = apiService.createCourse(username,token)
}
