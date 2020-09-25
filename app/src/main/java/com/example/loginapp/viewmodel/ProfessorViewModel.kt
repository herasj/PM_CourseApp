package com.example.loginapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.model.professor.DetailProfessor
import com.example.loginapp.model.professor.Professor
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository
import com.example.loginapp.repository.ProfessorRepository
import kotlinx.coroutines.launch

class ProfessorViewModel : ViewModel() {
    private val authrepository = AuthRepository()
    private val loginrepository = LoginRepository
    private val repository = ProfessorRepository()
    private val professors = mutableListOf<Professor>()
    val professorLiveData = MutableLiveData<List<Professor>>()
    var professorDetail: DetailProfessor = DetailProfessor()
    val professorDetailLiveData = MutableLiveData<DetailProfessor>()

    init {
        getProfessor()
    }

    fun getProfessor() {
        viewModelScope.launch {
            val response = authrepository.refreshToken(loginrepository.getToken().value!!)
            Log.d("VM_Token", "$response")
            if (response.valid == false) {
                Log.i("Status", "Is Not Valid")
                val email = loginrepository.getEmail().value!!
                val pass = loginrepository.getPassword().value!!
                Log.d("PreferenceEmail", "${loginrepository.getEmail().value}")
                Log.d("PreferencePass", "${loginrepository.getPassword().value}")
                val newResponse: AuthResponse = authrepository.signin(Login(email, pass))
                Log.d("VM_Token", "$newResponse")
                loginrepository.setToken("Bearer ${newResponse.token}")
                Log.d("PreferenceToken", "${loginrepository.getToken().value}")
            } else {
                Log.i("Status", "IsValid")
            }
            professors.addAll(
                repository.getProfessors(
                    loginrepository.getUsername().value!!,
                    loginrepository.getToken().value!!
                )
            )
            professorLiveData.postValue(professors)
        }
    }

    fun getProfessorInfo(professorId: String) {
        viewModelScope.launch {
            val response = authrepository.refreshToken(loginrepository.getToken().value!!)
            Log.d("VM_Token", "$response")
            if (response.valid == false) {
                Log.i("Status", "Is Not Valid")
                val email = loginrepository.getEmail().value!!
                val pass = loginrepository.getPassword().value!!
                Log.d("PreferenceEmail", "${loginrepository.getEmail().value}")
                Log.d("PreferencePass", "${loginrepository.getPassword().value}")
                val newResponse: AuthResponse = authrepository.signin(Login(email, pass))
                Log.d("VM_Token", "$newResponse")
                loginrepository.setToken("Bearer ${newResponse.token}")
                Log.d("PreferenceToken", "${loginrepository.getToken().value}")
            } else {
                Log.i("Status", "IsValid")
            }
            val professorInfo = repository.getProfessorInfo(
                loginrepository.getUsername().value!!,
                professorId,
                loginrepository.getToken().value!!
            )
            professorDetail = professorInfo
            professorDetailLiveData.postValue(professorInfo)
        }
    }

}