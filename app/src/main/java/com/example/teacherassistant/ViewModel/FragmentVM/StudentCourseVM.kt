package com.example.teacherassistant.ViewModel.FragmentVM

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class StudentCourseVM(application: Application): AndroidViewModel(application) {
    lateinit var readAllStudentsofCourse: LiveData<List<Student>>
    lateinit var readAllStudentsOutofCourse: LiveData<List<Student>>

    var currentStudent: Student? = null
    var currentCourse: Course? = null
    private val repository: StudentCourseRepository

    init {
        val studentcoursedao = AssistantDatabase.getDatabase(application).studentCourseDao()
        repository = StudentCourseRepository(studentcoursedao)
    }

    fun insertStudentCourseCrossRef(idc: Int, ids: Int){
        viewModelScope.launch {
            repository.addStudentCourseCrossRef(StudentCourseCrossRef(ids, idc))
        }
    }

    fun removeStudentCourseCrossRef(){
        if(currentStudent != null && currentCourse != null) {
            val cross: StudentCourseCrossRef =
                StudentCourseCrossRef(currentStudent!!.ids, currentCourse!!.idc)
            viewModelScope.launch {
                repository.removeStudentCourseCrossRef(cross)
            }
        }

    }
    fun getStudentsofCourse(course: Course){
        viewModelScope.launch {
            repository.getStudentsOfCourse(course)
        }
    }

    fun getCoursesOfStudent(student: Student){
        viewModelScope.launch {
            repository.getCoursesOfStudent(student)
        }
    }

    fun getSsOC(course: Course?){
        if (course != null){
            repository.getSsOfC(course)
            readAllStudentsofCourse = repository.readAllStudentsOfCourse
        }else{
            Log.v("err", "empty list")
        }
    }

    fun getStudentsOutOfCourse(course: Course?){
        if(currentCourse != null){
            repository.getStudentsOutOfCourse(currentCourse!!)
            readAllStudentsOutofCourse = repository.readAllStudentsOutOfCourse
        }
    }

    fun AddStudentToCourse(student: Student?, course: Course?){
        if(student != null && course != null){
            viewModelScope.launch {
                repository.addStudentCourseCrossRef(StudentCourseCrossRef(idc = course.idc, ids = student.ids))
            }
        }
    }
}