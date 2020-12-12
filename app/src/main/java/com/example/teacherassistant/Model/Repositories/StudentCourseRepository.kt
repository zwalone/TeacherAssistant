package com.example.teacherassistant.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Dao.StudentCourseDao
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseCrossRef

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {

    lateinit var readAllStudentsOfCourse : LiveData<List<Student>>
    lateinit var readAllStudentsOutOfCourse : LiveData<List<Student>>

    suspend fun addStudentCourseCrossRef(studentCourseCrossRef: StudentCourseCrossRef){
        studentCourseDao.insertStudentCourseCrossRef(studentCourseCrossRef)
    }

    suspend fun removeStudentCourseCrossRef(studentCourseCrossRef: StudentCourseCrossRef?){
        if(studentCourseCrossRef != null){
            studentCourseDao.deleteStudentCourseCrossRef(studentCourseCrossRef)
        }
    }

    fun getStudentsOutOfCourse(course: Course){
        readAllStudentsOutOfCourse = studentCourseDao.getStudentsOutOfCourse(course.idc)
    }

    fun getSsOfC(course: Course){
        readAllStudentsOfCourse = studentCourseDao.getStudentsOfCourse(course.idc)
    }
}