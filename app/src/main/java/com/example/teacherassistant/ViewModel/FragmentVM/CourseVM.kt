package com.example.teacherassistant.ViewModel.FragmentVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseVM(application: Application):AndroidViewModel(application) {
    val readAllCourses: LiveData<List<Course>>
    private val repository: CourseRepository
    var currentCourse: Course? = null

    init {
        val courseDao = AssistantDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
        readAllCourses = repository.getAllCourses
    }

    fun addCourse(course: Course){
        viewModelScope.launch {
            repository.addCourse(course)
        }
    }
    fun removeCourse(){
        viewModelScope.launch {
            repository.removeCourse(currentCourse)
        }
    }
    fun updateCourse(course: Course){
        viewModelScope.launch {
            repository.updateCourse(course)
        }
    }
    fun getCourseById(idc: Int){
        viewModelScope.launch {
            repository.getCourseById(idc)
        }
    }
}