package com.example.loginapp.services.api.students
import com.example.loginapp.model.student.Student
import com.example.loginapp.model.student.StudentDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentsApiService {
    private val studentApi = Retrofit.Builder()
        .baseUrl("https://movil-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StudentApi::class.java)

    suspend fun getStudent(dbId: String, token: String): List<Student> {
        return studentApi.getStudents(dbId, token)
    }

    suspend fun createStudent(dbId: String, token: String) {
        return studentApi.createStudent(dbId, token)
    }

    suspend fun getStudentInfo(dbId: String, studentId: String, token: String): StudentDetails {
        return studentApi.getStudentInfo(dbId, studentId, token)
    }

}