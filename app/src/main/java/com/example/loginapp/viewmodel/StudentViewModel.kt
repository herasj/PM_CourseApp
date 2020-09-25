package com.example.loginapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.model.student.Student
import com.example.loginapp.model.student.StudentDetails
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository
import com.example.loginapp.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val authrepository = AuthRepository()
    private val loginrepository = LoginRepository
    private val repository = StudentRepository()
    private val students = mutableListOf<Student>()
    val studentsLiveData = MutableLiveData<List<Student>>()
    var studentDetail: StudentDetails = StudentDetails()

    init {
        getStudent()
    }

    fun getStudent() {
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
            students.addAll(
                repository.getStudents(
                    loginrepository.getUsername().value!!,
                    loginrepository.getToken().value!!
                )
            )
            studentsLiveData.postValue(students)
        }
    }

    fun getStudentInfo(studentId: String) {
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
            val studentInfo = repository.getStudentInfo(
                loginrepository.getUsername().value!!,
                studentId,
                loginrepository.getToken().value!!
            )
            studentDetail = studentInfo
        }
    }
}