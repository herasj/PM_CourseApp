package com.example.loginapp.services.api.courses
import com.example.loginapp.model.Post
import com.example.loginapp.model.course.Course
import com.example.loginapp.model.course.CourseDetail
import retrofit2.http.*

interface CoursesApi {
    @GET("{dbId}/courses")
    suspend fun getCourses(@Path("dbId") dbId: String, @Header("Authorization") token: String): List<Course>

    @POST("{dbId}/courses")
    suspend fun createCourse(@Path("dbId") dbId: String, @Header("Authorization") token: String): Course

    @GET("{dbId}/courses/{courseId}")
    suspend fun getCourseInfo(@Path("dbId") dbId: String, @Path("courseId") courseId: String, @Header("Authorization") token: String): CourseDetail
}