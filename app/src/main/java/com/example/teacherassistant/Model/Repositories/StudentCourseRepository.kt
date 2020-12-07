package com.example.teacherassistant.Model.Repositories

import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Dao.StudentCourseDao
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseCrossRef

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {

    suspend fun addStudentCourseCrossRef(studentCourseCrossRef: StudentCourseCrossRef){
        studentCourseDao.insertStudentCourseCrossRef(studentCourseCrossRef)
    }
     fun getStudentsOfCourse(course: Course){
         studentCourseDao.getStudentsOfCourse(course.idc)
     }
    fun getCoursesOfStudent(student: Student){
        studentCourseDao.getCoursesOfStudent(student.ids)
    }
}