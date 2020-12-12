package com.example.teacherassistant.ViewModel.FragmentVM

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Repositories.GradeRepository
import kotlinx.coroutines.launch

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

    fun deleteGrade(grade: Grade?){
        if(grade != null){
            viewModelScope.launch {
                repository.removeGrade(grade)
            }
        }else{
            Toast.makeText(getApplication(),"Select grade", Toast.LENGTH_LONG).show()
        }
    }

    fun readAllStudentGrades(student: Student?){
        if(student != null){
            repository.getStudentsGrade(student)
            readAllStudentGrade = repository.getAllStudentGrade
        }
    }
}