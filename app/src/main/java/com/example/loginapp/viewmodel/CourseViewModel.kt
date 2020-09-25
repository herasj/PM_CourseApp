package com.example.loginapp.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.AuthResponse
import com.example.loginapp.model.Login
import com.example.loginapp.model.Post
import com.example.loginapp.model.course.Course
import com.example.loginapp.model.course.CourseDetail
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.CourseRepository
import com.example.loginapp.repository.LoginRepository
import com.example.loginapp.repository.StudentRepository
import kotlinx.coroutines.launch
import java.lang.Error

class CourseViewModel: ViewModel() {
    private val repository = CourseRepository()
    private val authrepository = AuthRepository()
    private val studentRepository = StudentRepository()
    private val loginrepository = LoginRepository
    val courses = mutableListOf<Course>()
    val coursesLiveData = MutableLiveData<List<Course>>()
    var courseDetail: CourseDetail = CourseDetail()
    val courseDetailLiveData = MutableLiveData<CourseDetail>()
    init {
        getCourses()
    }

    fun getCourses() {
        viewModelScope.launch {
                val response = authrepository.refreshToken(loginrepository.getToken().value!!)
                Log.d("VM_Token", "$response")
                if(response.valid==false){
                    Log.i("Status","Is Not Valid")
                    val email = loginrepository.getEmail().value!!
                    val pass = loginrepository.getPassword().value!!
                    Log.d("PreferenceEmail","${loginrepository.getEmail().value}")
                    Log.d("PreferencePass","${loginrepository.getPassword().value}")
                    val newResponse: AuthResponse = authrepository.signin(Login(email,pass))
                    Log.d("VM_Token", "$newResponse")
                    loginrepository.setToken("Bearer ${newResponse.token}")
                    Log.d("PreferenceToken","${loginrepository.getToken().value}")
                }else{
                    Log.i("Status","IsValid")
                }
                val coursesResponse = repository.getCourses(loginrepository.getUsername().value!!,loginrepository.getToken().value!!)
                courses.clear()
                courses.addAll(coursesResponse)
                coursesLiveData.postValue(courses)
        }
    }

    fun getCourseInfo(courseId: String) {
        viewModelScope.launch {
            val response = authrepository.refreshToken(loginrepository.getToken().value!!)
            Log.d("VM_Token", "$response")
            if(response.valid==false){
                Log.i("Status","Is Not Valid")
                val email = loginrepository.getEmail().value!!
                val pass = loginrepository.getPassword().value!!
                Log.d("PreferenceEmail","${loginrepository.getEmail().value}")
                Log.d("PreferencePass","${loginrepository.getPassword().value}")
                val newResponse: AuthResponse = authrepository.signin(Login(email,pass))
                Log.d("VM_Token", "$newResponse")
                loginrepository.setToken("Bearer ${newResponse.token}")
                Log.d("PreferenceToken","${loginrepository.getToken().value}")
            }else{
                Log.i("Status","IsValid")
            }
            val courseInfo = repository.getCourseInfo(loginrepository.getUsername().value!!,loginrepository.getToken().value!!,courseId)
            Log.i("Course", "$courseInfo")
            courseDetail = courseInfo
            courseDetailLiveData.postValue(courseInfo)
        }
    }

    fun createCourse(){
        viewModelScope.launch {
            val response = authrepository.refreshToken(loginrepository.getToken().value!!)
            Log.d("VM_Token", "$response")
            if(response.valid==false){
                Log.i("Status","Is Not Valid")
                val email = loginrepository.getEmail().value!!
                val pass = loginrepository.getPassword().value!!
                Log.d("PreferenceEmail","${loginrepository.getEmail().value}")
                Log.d("PreferencePass","${loginrepository.getPassword().value}")
                val newResponse: AuthResponse = authrepository.signin(Login(email,pass))
                Log.d("VM_Token", "$newResponse")
                loginrepository.setToken("Bearer ${newResponse.token}")
                Log.d("PreferenceToken","${loginrepository.getToken().value}")
            }else{
                Log.i("Status","IsValid")
            }
            val course = repository.createCourse(loginrepository.getUsername().value!!,loginrepository.getToken().value!!)
            Log.i("newCourse", "$course")
            courses.add(course)
            coursesLiveData.postValue(courses)
        }
    }

    fun createStudent(courseId: String) {
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
            val newStudent = studentRepository.createStudent(
                loginrepository.getUsername().value!!,
                loginrepository.getToken().value!!,
                courseId
            )
            getCourseInfo(courseId)
            //students.add(newStudent)
            //studentsLiveData.postValue(newStudent)
        }
    }
}