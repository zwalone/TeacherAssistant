package com.example.teacherassistant.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Dao.GradeDao
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentGrade
import com.example.teacherassistant.Model.StudentGradeCourse
import java.util.*

class GradeRepository(private val gradeDao: GradeDao) {

    val getAllGrades:LiveData<List<Grade>> = gradeDao.getAllGrades()
    var getAllTodaysgrades: LiveData<List<StudentGrade>>
    lateinit var getAllStudentGrade: LiveData<List<StudentGradeCourse>>

    init {
        val time = Calendar.getInstance()
        time.time = Date()
        time[Calendar.HOUR_OF_DAY] = 0
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0
        time[Calendar.MILLISECOND] = 0
        val begin = Date(time.timeInMillis)
        time[Calendar.HOUR_OF_DAY] = 23
        time[Calendar.MINUTE] = 59
        time[Calendar.SECOND] = 59
        time[Calendar.MILLISECOND] = 999
        val end = Date(time.timeInMillis)
        getAllTodaysgrades = gradeDao.getTodayGrade(today = begin, dayBefore = end)
    }

    suspend fun addGrade(grade: Grade){
        gradeDao.insertGrade(grade)
    }
    suspend fun removeGrade(grade: Grade){
        gradeDao.deleteGrade(grade)
    }

    fun getStudentsGrade(student: Student){
        getAllStudentGrade = gradeDao.getStudentGrades(student.ids)
    }
}