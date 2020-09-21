package com.example.loginapp.services.api.students
import com.example.loginapp.model.student.Student
import com.example.loginapp.model.student.StudentDetails
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApi {
    @GET("{dbId}/students")
    suspend fun getStudents(@Path("dbId") dbId: String, @Header("Authorization") token: String): List<Student>

    @POST("{dbId}/students")
    suspend fun createStudent(@Path("dbId") dbId: String, @Header("Authorization") token: String)

    @GET("{dbId}/students/{studentId}")
    suspend fun getStudentInfo(@Path("dbId") dbId: String, @Path("studentId") studentId: String, @Header("Authorization") token: String): StudentDetails
}