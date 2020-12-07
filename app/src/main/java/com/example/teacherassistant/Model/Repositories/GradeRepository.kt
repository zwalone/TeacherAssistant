package com.example.teacherassistant.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Dao.GradeDao
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.Student


//VALIDACJA
class GradeRepository(private val gradeDao: GradeDao) {

    val getAllGrades:LiveData<List<Grade>> = gradeDao.getAllGrades()

    suspend fun addGrade(grade: Grade){
        gradeDao.insertGrade(grade)
    }
    suspend fun removeGrade(grade: Grade){
        gradeDao.deleteGrade(grade)
    }
    suspend fun updateGrade(grade: Grade){
        gradeDao.updateGrade(grade)
    }

    fun getTodayGrades(){
        gradeDao.getTodayGrade()
    }

    fun getStudentsGrade(student: Student){
        gradeDao.getStudentGrades(student.ids)
    }
}