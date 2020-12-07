package com.example.teacherassistant.Model.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Dao.CourseDao
import com.example.teacherassistant.Model.Dao.GradeDao
import com.example.teacherassistant.Model.Dao.StudentCourseDao
import com.example.teacherassistant.Model.Dao.StudentDao

//WALIDACJA !!!!

class StudentRepository(private val studentDao: StudentDao) {
    val getAllStudents:LiveData<List<Student>> = studentDao.allStudents()

    suspend fun addStudent(student: Student){
        studentDao.insertStudent(student)
        Log.v("student", "added")
    }
    suspend fun removeStudent(student: Student?){
        if(student != null){
            studentDao.deleteStudent(student)
            Log.v("student", "removed")
        }
    }
    suspend fun updateStudent(student: Student?){
        if(student != null){
            studentDao.updateStudent(student)
            Log.v("student", "updated")
        }
    }
    fun getStudentById(id: Int):Student = studentDao.getStudentById(id)
}