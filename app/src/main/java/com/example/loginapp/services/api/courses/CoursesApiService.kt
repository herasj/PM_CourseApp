package com.example.loginapp.services.api.courses
import com.example.loginapp.model.course.CourseDetail
import com.example.loginapp.model.course.Course
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CourseApiService {
    private val postsApi = Retrofit.Builder()
        .baseUrl("https://movil-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoursesApi::class.java)

    suspend fun getCourses(dbId: String, token: String): List<Course> {
        return postsApi.getCourses(dbId, token)
    }

    suspend fun createCourse(dbId: String, token: String): Course {
        return postsApi.createCourse(dbId, token)
    }

    suspend fun getCourseInfo(dbId: String, courseId: String, token: String): CourseDetail {
        return postsApi.getCourseInfo(dbId, courseId,token)
    }

}