package com.example.loginapp.repository

import com.example.loginapp.services.api.professors.ProfessorsApiService
import com.example.loginapp.util.PreferenceProvider

class ProfessorRepository {

    private val apiService = ProfessorsApiService()

    suspend fun getProfessors() = apiService.getProfessors(PreferenceProvider.getUsername()!!, PreferenceProvider.getToken()!!)

    suspend fun getProfessorInfo(professorId: String) = apiService.getProfessorInfo(PreferenceProvider.getUsername()!!, professorId , PreferenceProvider.getToken()!!)

}