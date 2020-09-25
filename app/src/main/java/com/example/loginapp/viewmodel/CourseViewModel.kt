package com.example.loginapp.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.Post
import com.example.loginapp.model.course.Course
import com.example.loginapp.model.course.CourseDetail
import com.example.loginapp.repository.AuthRepository
import com.example.loginapp.repository.CourseRepository
import kotlinx.coroutines.launch
import java.lang.Error

class CourseViewModel: ViewModel() {
    private val repository = CourseRepository()
    private val authrepository = AuthRepository()
    val courses = mutableListOf<Course>()
    val coursesLiveData = MutableLiveData<List<Course>>()
    var courseDetail: CourseDetail = CourseDetail()
    init {
        getCourses()
    }

    fun getCourses() {
        viewModelScope.launch {
            try{
                Log.d("Mkya", "Mkyaaa")
                authrepository.refreshToken()
                Log.d("grave", "gravelavuelta")
                courses.addAll(repository.getCourses())
                coursesLiveData.postValue(courses)
            }catch (error: Error){
                Log.d("Response Courses", error.toString())
            }

        }
    }

    fun getCourseInfo(courseId: String) {
        viewModelScope.launch {
            authrepository.refreshToken()
            val courseInfo = repository.getCourseInfo(courseId)
            courseDetail = courseInfo
        }
    }

    fun createCourse(){
        viewModelScope.launch {
            authrepository.refreshToken()
            val course = repository.createCourse()
            courses.add(course)
            coursesLiveData.postValue(courses)
        }
    }
}