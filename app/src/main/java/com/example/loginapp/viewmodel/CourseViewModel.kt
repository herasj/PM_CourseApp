package com.example.loginapp.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.model.Post
import com.example.loginapp.model.course.Course
import com.example.loginapp.model.course.CourseDetail
import com.example.loginapp.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel: ViewModel() {
    private val repository = CourseRepository()
    private val courses = mutableListOf<Course>()
    val coursesLiveData = MutableLiveData<List<Course>>()
    var courseDetail: CourseDetail = CourseDetail()
    init {
        getCourses()
    }

    fun getCourses() {
        viewModelScope.launch {
            courses.addAll(repository.getCourses())
            coursesLiveData.postValue(courses)
        }
    }

    fun getCourseInfo(courseId: String) {
        viewModelScope.launch {
            val courseInfo = repository.getCourseInfo(courseId)
            courseDetail = courseInfo
        }
    }

    fun createCourse(){
        viewModelScope.launch {
            val course = repository.createCourse()
            courses.add(course)
            coursesLiveData.postValue(courses)
        }
    }
}