package com.example.loginapp.services.api.professors
import com.example.loginapp.model.professor.DetailProfessor
import com.example.loginapp.model.professor.Professor
import com.example.loginapp.services.api.professors.ProfessorApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfessorsApiService {
    private val proApi = Retrofit.Builder()
        .baseUrl("https://movil-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProfessorApi::class.java)

    suspend fun getProfessors(dbId: String, token: String): List<Professor> {
        return proApi.getProfessors(dbId, token)
    }

    suspend fun getProfessorInfo(dbId: String, professorId: String, token: String): DetailProfessor {
        return proApi.getProfessorInfo(dbId, professorId, token)
    }

}