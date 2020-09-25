package com.example.loginapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.student.Student
import com.example.loginapp.model.student.StudentDetails
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.LoginRepository
import com.example.loginapp.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel: ViewModel() {
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
            authrepository.refreshToken(loginrepository.getToken().value!!)
            students.addAll(repository.getStudents())
            studentsLiveData.postValue(students)
        }
    }

    fun getStudentInfo(studentId: String) {
        viewModelScope.launch {
            authrepository.refreshToken(loginrepository.getToken().value!!)
            val studentInfo = repository.getStudentInfo(studentId)
            studentDetail = studentInfo
        }
    }

    fun createStudent() {
        viewModelScope.launch {
            val newStudent = repository.createStudent()
            //students.add(newStudent)
            //studentsLiveData.postValue(newStudent)
        }
    }
}