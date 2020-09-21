package com.example.loginapp.services.api.professors
import com.example.loginapp.model.professor.Professor
import com.example.loginapp.model.professor.DetailProfessor
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProfessorApi {
    @GET("{dbId}/professors")
    suspend fun getProfessors(@Path("dbId") dbId: String, @Header("Authorization") token: String): List<Professor>

    @GET("{dbId}/professors/{professorId}")
    suspend fun getProfessorInfo(@Path("dbId") dbId: String, @Path("professorId") professorId: String, @Header("Authorization") token: String): DetailProfessor
}