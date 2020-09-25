package com.example.loginapp.repository

import com.example.loginapp.services.api.professors.ProfessorsApiService
import com.example.loginapp.util.PreferenceProvider

class ProfessorRepository {

    private val apiService = ProfessorsApiService()

    suspend fun getProfessors(username: String, token: String) = apiService.getProfessors(username,token)

    suspend fun getProfessorInfo(username: String, professorId: String, token: String) = apiService.getProfessorInfo(username, professorId , token)

}