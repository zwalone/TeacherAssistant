package com.example.teacherassistant.ViewModel.FragmentVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Repositories.GradeRepository
import com.example.teacherassistant.View.ListStudents
import kotlinx.coroutines.launch
import java.util.*

class GradeVM(application: Application): AndroidViewModel(application) {
    val readAllGrades : LiveData<List<Grade>>
    val readTodayGrades: LiveData<List<StudentGrade>>
    lateinit var readAllStudentGrade: LiveData<List<StudentGradeCourse>>
    private val repository: GradeRepository
    var currentGrade: Grade? = null
    var currentStudentGrade: StudentGrade? = null

    init {
        val gradedao = AssistantDatabase.getDatabase(application).gradeDao()
        repository = GradeRepository(gradedao)
        readAllGrades = repository.getAllGrades
        readTodayGrades = repository.getAllTodaysgrades
    }

    fun insertGrade(grade: Grade){
        viewModelScope.launch {
            repository.addGrade(grade)
        }
    }

    fun deleteGrade(grade: Grade){
        viewModelScope.launch {
            repository.removeGrade(grade)
        }
    }

    fun updateGrade(grade: Grade){
        viewModelScope.launch {
            repository.updateGrade(grade)
        }
    }

    fun readAllStudentGrades(student: Student?){
        if(student != null){
            repository.getStudentsGrade(student)
            readAllStudentGrade = repository.getAllStudentGrade
        }
    }
}