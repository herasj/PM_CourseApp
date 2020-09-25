package com.example.loginapp.repository
import android.util.Log
import com.example.loginapp.model.course.Course
import com.example.loginapp.services.api.courses.CourseApiService
import com.example.loginapp.util.PreferenceProvider
class CourseRepository {

    private val apiService = CourseApiService()
    private val authRepo = LoginRepository
    suspend fun getCourses() : List<Course> {
        val user:String= authRepo.stateUsername;
        val token: String = authRepo.stateToken;
        Log.d("user", user)
        Log.d("token", token)
        return apiService.getCourses(user, token)
    }

    suspend fun getCourseInfo(courseId: String) = apiService.getCourseInfo(PreferenceProvider.getUsername()!!, courseId, PreferenceProvider.getToken()!!)

    suspend fun createCourse() = apiService.createCourse(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)
}
