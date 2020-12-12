package com.example.teacherassistant.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Dao.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
    val getAllStudents:LiveData<List<Student>> = studentDao.allStudents()

    suspend fun addStudent(student: Student){
        studentDao.insertStudent(student)
    }
    suspend fun removeStudent(student: Student?){
        if(student != null){
            studentDao.deleteStudent(student)
        }
    }
    suspend fun updateStudent(student: Student?){
        if(student != null){
            studentDao.updateStudent(student)
        }
    }

}