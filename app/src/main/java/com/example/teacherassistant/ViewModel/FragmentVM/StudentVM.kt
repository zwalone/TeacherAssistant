package com.example.teacherassistant.ViewModel.FragmentVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Repositories.StudentRepository
import com.example.teacherassistant.Model.Student
import kotlinx.coroutines.launch
import android.util.Log

class StudentVM(application: Application):AndroidViewModel(application) {

    val readAllStudents: LiveData<List<Student>>
    private val repository: StudentRepository
    var currentStudent: Student? = null

    init {
        val studentDao = AssistantDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        readAllStudents = repository.getAllStudents
    }
    fun addStudent(student: Student){
        viewModelScope.launch {
            repository.addStudent(student)
        }
    }
    fun removeStudent(){
        viewModelScope.launch {
            repository.removeStudent(currentStudent)
        }
    }
    fun updateStudent(student: Student){
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }
    fun getStudentById(ids: Int){
        viewModelScope.launch {
            repository.getStudentById(ids)
        }
    }

}