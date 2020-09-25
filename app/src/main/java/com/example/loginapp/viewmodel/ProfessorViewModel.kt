package com.example.loginapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.professor.DetailProfessor
import com.example.loginapp.model.professor.Professor
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.ProfessorRepository
import kotlinx.coroutines.launch

class ProfessorViewModel: ViewModel() {
    private val authrepository = AuthRepository()
    private val repository = ProfessorRepository()
    private val professors = mutableListOf<Professor>()
    val professorLiveData = MutableLiveData<List<Professor>>()
    var professorDetail: DetailProfessor = DetailProfessor()
    init {
        getProfessor()
    }

    fun getProfessor() {
        viewModelScope.launch {
            authrepository.refreshToken()
            professors.addAll(repository.getProfessors())
            professorLiveData.postValue(professors)
        }
    }

    fun getProfessorInfo(professorId: String) {
        viewModelScope.launch {
            authrepository.refreshToken()
            val professorInfo = repository.getProfessorInfo(professorId)
            professorDetail = professorInfo
        }
    }

}